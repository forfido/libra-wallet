package com.palibra.walletapi.domain.wallet;

import com.palibra.walletapi.domain.account.Account;
import com.palibra.walletapi.domain.account.GeneratedAccount;
import com.palibra.walletapi.domain.account.MnemonicAccount;
import dev.jlibra.mnemonic.*;
import org.bouncycastle.jcajce.provider.asymmetric.edec.BCEdDSAPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.edec.BCEdDSAPublicKey;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

@Service
public class LibraWalletService {

    public LibraWalletService() {
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

    public byte[] findLibraAccount(String indexOrAddress) {
        if (indexOrAddress.length() > 12) {
            return Hex.decode(indexOrAddress.getBytes(Charset.forName("UTF-8")));
        }

        return null;
    }

}
