package com.palibra.walletapi.domain.wallet;

import com.palibra.walletapi.domain.account.Account;
import com.palibra.walletapi.domain.account.GeneratedAccount;
import com.palibra.walletapi.domain.account.MnemonicAccount;
import dev.jlibra.KeyUtils;
import dev.jlibra.mnemonic.*;
import org.bouncycastle.jcajce.provider.asymmetric.edec.BCEdDSAPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.edec.BCEdDSAPublicKey;
import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.ArrayList;

@Service
public class LibraWalletService {

    private ArrayList<Account> accounts;

    public LibraWalletService() {
        this.accounts = new ArrayList<>();
    }

    public String createAccount() {
        try {
            SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest256();
            KeyPairGenerator kpGen = KeyPairGenerator.getInstance("Ed25519", "BC");
            KeyPair keyPair = kpGen.generateKeyPair();
            BCEdDSAPrivateKey privateKey = (BCEdDSAPrivateKey) keyPair.getPrivate();
            BCEdDSAPublicKey publicKey = (BCEdDSAPublicKey) keyPair.getPublic();
            String libraAddress = new String(Hex.encode(digestSHA3.digest(KeyUtils.stripPublicKeyPrefix(publicKey.getEncoded()))));
//            System.out.println();
//            System.out.println("Created account with Libra address: " + libraAddress);
//            System.out.println("Public key  " + new String(Hex.encode(publicKey.getEncoded())));
//            System.out.println("Private key " + new String(Hex.encode(privateKey.getEncoded())));
//            System.out.println();
            accounts.add(new GeneratedAccount(privateKey, publicKey));

            return libraAddress;
        } catch (Exception ex) {
            throw new IllegalStateException("Cannot create account.", ex);
        }
    }

    public void createAccountFromMnemonic(String mnemonic, int child) {
        try {
            Seed seed = new Seed(Mnemonic.fromString(mnemonic), "LIBRA");
            LibraKeyFactory libraKeyFactory = new LibraKeyFactory(seed);
            ExtendedPrivKey extendedPrivKey = libraKeyFactory.privateChild(new ChildNumber(child));
            String libraAddress = extendedPrivKey.getAddress();
            System.out.println();
            System.out.println("Recovered account from mnemonic with Libra address: " + libraAddress);
            System.out.println("Public key  " + new String(Hex.encode(extendedPrivKey.publicKey.getEncoded())));
            System.out.println("Private key " + new String(Hex.encode(extendedPrivKey.privateKey.getEncoded())));
            System.out.println();
            accounts.add(new MnemonicAccount(extendedPrivKey));
        } catch (Exception ex) {
            throw new IllegalStateException("Cannot create account.", ex);
        }
    }

    public void listAccounts() {
        for (int i=0; i<accounts.size(); i++) {
            System.out.println();
            System.out.println("Account     " + i);
            System.out.println("Address     " + new String(Hex.encode(accounts.get(i).getAddress())));
            System.out.println("Public key  " + new String(Hex.encode(accounts.get(i).getPublicKey())));
            System.out.println("Private key " + new String(Hex.encode(accounts.get(i).getPrivateKey())));
        }
        System.out.println();
    }

    public byte[] findLibraAccount(String indexOrAddress) {
        if (indexOrAddress.length() > 12) {
            return Hex.decode(indexOrAddress.getBytes(Charset.forName("UTF-8")));
        } else {
            return accounts.get(Integer.parseInt(indexOrAddress)).getAddress();
        }
    }

    public Account getAccountAt(int index) {
        return accounts.get(index);
    }

}
