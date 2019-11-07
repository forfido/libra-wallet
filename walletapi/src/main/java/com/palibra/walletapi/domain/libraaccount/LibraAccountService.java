package com.palibra.walletapi.domain.libraaccount;

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
import java.nio.charset.Charset;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

@Service
public class LibraAccountService {

    @Autowired
    private LibraAccountRepository libraAccountRepository;

    @Autowired
    private JLibraUtil jLibraUtil;

    @Autowired
    private PeerToPeerTransfer peerToPeerTransfer;

    public LibraAccountService() {
    }

    public Account createAccount() {
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

    public void listAccounts() {
    }

    //리브라 주소로 추정되는 값을 주소 형식으로 변환
    public byte[] findAccount(String assumeAddress) {
        if (assumeAddress.length() > 12) {
            return Hex.decode(assumeAddress.getBytes(Charset.forName("UTF-8")));
        }

        return null;
    }

    public LibraAccount findAccount(Long userId) {

        return libraAccountRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("LibraAccount", "id", userId));
    }

    public Long getBalance(String libraAddress){

        return jLibraUtil.findBalance(libraAddress);
    }
//
//    public void transfer(String senderAddressOrIndex, String receiverAddress, BigInteger amount, @ShellOption(defaultValue = "-1") BigInteger gasUnitPrice, @ShellOption(defaultValue = "-1") BigInteger maxGasAmount) {
//        Account senderAccount = getAccountAt(Integer.parseInt(senderAddressOrIndex));
//        receiverAddress = Hex.toHexString(wallet.findLibraAccount(receiverAddress));
//        PublicKey publicKey = KeyUtils.publicKeyFromHexString(new String(Hex.encode(senderAccount.getPublicKey())));
//        PrivateKey privateKey = KeyUtils.privateKeyFromHexString(new String(Hex.encode(senderAccount.getPrivateKey())));
//        PeerToPeerTransfer.PeerToPeerTransferReceipt receipt = peerToPeerTransfer.transferFunds(receiverAddress, amount.longValue() * 1_000_000, publicKey, privateKey, gasUnitPrice.longValue(), maxGasAmount.longValue());
//        //System.out.println(receipt.getStatus());
//    }
}
