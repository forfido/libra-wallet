package com.palibra.walletapi.domain.libraaccount;

import com.palibra.walletapi.domain.libraaccount.payload.LibraBalance;
import com.palibra.walletapi.domain.libraaccount.payload.TransferRequest;
import com.palibra.walletapi.domain.user.User;
import com.palibra.walletapi.exception.ResourceNotFoundException;
import dev.jlibra.KeyUtils;
import dev.jlibra.mnemonic.*;
import dev.jlibra.spring.action.PeerToPeerTransfer;
import dev.jlibra.util.JLibraUtil;
import org.bouncycastle.jcajce.provider.asymmetric.edec.BCEdDSAPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.edec.BCEdDSAPublicKey;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import static com.sun.xml.fastinfoset.stax.events.Util.isEmptyString;

@Service
public class LibraAccountService {

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

    public LibraAccount findAccount(Long userId, String libraAccountName) {

        return libraAccountRepository.findByUserIdAndName(userId, libraAccountName).orElseThrow(() -> new ResourceNotFoundException(libraAccountName, "id", userId));
    }

    public LibraBalance getBalance(String libraAddress) {
        LibraBalance libraBalance = new LibraBalance();
        Long balance = jLibraUtil.findBalance(libraAddress);
        libraBalance.setLibra(balance/1000000);
        libraBalance.setLibraMicro(balance);
        return libraBalance;
    }

    public Account createLibraUserAccount(User user, String libraAccountName) {
        //Create Libra Address by JLibra
        Account libraAccount = createAccount();

        if (isEmptyString(libraAccountName)) {
            libraAccountName = "LibraAccount";
        }
        //Save on Palibra wallet
        LibraAccount account = LibraAccount.createAccount(libraAccountName, libraAccount.getAddress(), libraAccount.getPrivateKey(), libraAccount.getPublicKey(), user);
        libraAccountRepository.save(account);

        //1000 Test LBR provided
        jLibraUtil.mint(Hex.toHexString(account.getLibraAddress()), 1000L);

        return libraAccount;
    }

    public PeerToPeerTransfer.PeerToPeerTransferReceipt.Status transferToMember(Long senderAccountId, TransferRequest transferRequest) {
        BigInteger gasUnitPrice = new BigInteger("-1");
        BigInteger maxGasAmount = new BigInteger("-1");

        LibraAccount senderAccount = libraAccountRepository.findById(senderAccountId).orElseThrow(() -> new ResourceNotFoundException("LibraAccount", "accountId", senderAccountId));
        LibraAccount receiverAccount = findAccount(transferRequest.getReceiverUserId(), ""); //TODO: Receive account name

        String receiverAddress = Hex.toHexString(receiverAccount.getLibraAddress());
        PublicKey publicKey = KeyUtils.publicKeyFromHexString(new String(Hex.encode(senderAccount.getPublicKey())));
        PrivateKey privateKey = KeyUtils.privateKeyFromHexString(new String(Hex.encode(senderAccount.getPrivateKey())));
        PeerToPeerTransfer.PeerToPeerTransferReceipt receipt = peerToPeerTransfer.transferFunds(receiverAddress, transferRequest.getAmount().longValue() * 1_000_000, publicKey, privateKey, gasUnitPrice.longValue(), maxGasAmount.longValue());
        return receipt.getStatus();
    }

    public Long mint(String address, Long amount) {
        return jLibraUtil.mint(address, amount);
    }
}
