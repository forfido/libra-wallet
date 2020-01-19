package com.palibra.walletapi.domain.libraaccount;

import com.palibra.walletapi.domain.libraaccount.payload.LibraBalance;
import com.palibra.walletapi.domain.libraaccount.payload.TransferRequest;
import com.palibra.walletapi.domain.user.User;
import com.palibra.walletapi.exception.ErrorHandlerException;
import com.palibra.walletapi.exception.ResourceNotFoundException;
import dev.jlibra.admissioncontrol.transaction.result.LibraTransactionException;
import dev.jlibra.admissioncontrol.transaction.result.SubmitTransactionResult;
import dev.jlibra.mnemonic.*;
import dev.jlibra.serialization.ByteSequence;
import dev.jlibra.spring.action.PeerToPeerTransfer;
import dev.jlibra.util.JLibraUtil;
import org.bouncycastle.jcajce.provider.asymmetric.edec.BCEdDSAPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.edec.BCEdDSAPublicKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import static dev.jlibra.KeyUtils.privateKeyFromByteSequence;
import static dev.jlibra.KeyUtils.publicKeyFromByteSequence;

@Service
public class LibraAccountService {

    private static final Logger log = LoggerFactory.getLogger(LibraAccountService.class);

    @Autowired
    private LibraAccountRepository libraAccountRepository;

    @Autowired
    private PeerToPeerTransfer peerToPeerTransfer;

    @Autowired
    private JLibraUtil jLibraUtil;

    public LibraAccountService() {}

    private Account createAccount() {
        try {
            KeyPairGenerator kpGen = KeyPairGenerator.getInstance("Ed25519", "BC");
            KeyPair keyPair = kpGen.generateKeyPair();
            BCEdDSAPrivateKey privateKey = (BCEdDSAPrivateKey) keyPair.getPrivate();
            BCEdDSAPublicKey publicKey = (BCEdDSAPublicKey) keyPair.getPublic();
            return new GeneratedAccount(privateKey, publicKey);
        } catch (Exception ex) {
            throw new IllegalStateException("Cannot create account.", ex);
        }
    }

    public Account createAccountFromMnemonic(String mnemonic, int child) {
        try {
            Seed seed = new Seed(Mnemonic.fromString(mnemonic), "LIBRA");
            LibraKeyFactory libraKeyFactory = new LibraKeyFactory(seed);
            ExtendedPrivKey extendedPrivKey = libraKeyFactory.privateChild(new ChildNumber(child));
            return new MnemonicAccount(extendedPrivKey);
        } catch (Exception ex) {
            throw new IllegalStateException("Cannot create account.", ex);
        }
    }

    public LibraAccount findAccount(Long userId) {

        return libraAccountRepository.findByUserId(userId).orElseThrow(() -> new ResourceNotFoundException("LibraAccount", "userId", userId));
    }

    public LibraBalance getBalance(String libraAddress) {
        LibraBalance libraBalance = new LibraBalance();
        Long balance = jLibraUtil.findBalance(libraAddress);
        libraBalance.setLibra(balance/1000000);
        libraBalance.setLibraMicro(balance);
        libraBalance.setLibraAddress(libraAddress);

        log.info(libraBalance.toString());

        return libraBalance;
    }

    public Account createLibraUserAccount(User user) {
        //Create Libra Address by JLibra
        Account libraAccount = createAccount();
        String libraAccountName = "LibraAccount";

        //Save on Palibra wallet
        LibraAccount account = LibraAccount.createAccount(libraAccountName, libraAccount.getAddress(), libraAccount.getPrivateKey(), libraAccount.getPublicKey(), user);
        libraAccountRepository.save(account);

        return libraAccount;
    }

    public String transferToMember(Long senderAccountId, TransferRequest transferRequest) throws LibraTransactionException {
        BigInteger gasUnitPrice = new BigInteger("-1");
        BigInteger maxGasAmount = new BigInteger("-1");

        LibraAccount senderAccount = libraAccountRepository.findById(senderAccountId).orElseThrow(() -> new ResourceNotFoundException("LibraAccount", "accountId", senderAccountId));
        String receiverAddress = transferRequest.getLibraAddress();


        if (senderAccount.getLibraAddressToString().compareToIgnoreCase(receiverAddress) == 0) {
            //log.info("Sender : " + senderAccount.getLibraAddressToString() + " Receiver :  " + receiverAddress);
            throw new ErrorHandlerException("Not available transfer to yourself");
        }

        PublicKey publicKey = publicKeyFromByteSequence(ByteSequence.from(senderAccount.getPublicKey()));
        PrivateKey privateKey = privateKeyFromByteSequence(ByteSequence.from(senderAccount.getPrivateKey()));

        SubmitTransactionResult receipt = peerToPeerTransfer.transferFunds(receiverAddress, transferRequest.getAmount().longValue() * 1_000_000, publicKey, privateKey, gasUnitPrice.longValue(), maxGasAmount.longValue());

        log.info(receipt.toString());

        //new String(receipt.getValidatorId()) validateId 가 빈값인 상태, validatorId는 gRpc의 응답메세지 값
        return receipt.toString();
    }

    public Long mint(String address, Long amount) {
        return jLibraUtil.mint( address, amount);
    }
}
