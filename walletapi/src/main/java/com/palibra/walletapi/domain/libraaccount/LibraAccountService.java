package com.palibra.walletapi.domain.libraaccount;

import com.palibra.walletapi.domain.user.User;
import com.palibra.walletapi.exception.ResourceNotFoundException;
import dev.jlibra.mnemonic.*;
import dev.jlibra.util.JLibraUtil;
import org.bouncycastle.jcajce.provider.asymmetric.edec.BCEdDSAPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.edec.BCEdDSAPublicKey;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

@Service
public class LibraAccountService {

    @Autowired
    private LibraAccountRepository libraAccountRepository;

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
        return libraAccountRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("LibraAccount", "id", userId));
    }

    public Long getBalance(String libraAddress) {
        return jLibraUtil.findBalance(libraAddress);
    }

    public Account createLibraUserAccount(User user) {
        //Create Libra Address by JLibra
        Account libraAccount = createAccount();

        //Save on Palibra wallet
        LibraAccount account = LibraAccount.createAccount("LibraWallet", libraAccount.getAddress(), libraAccount.getPrivateKey(), libraAccount.getPublicKey(), user);
        libraAccountRepository.save(account);

        //1000 Test LBR provided
        jLibraUtil.mint(Hex.toHexString(account.getLibraAddress()), 1000L);

        return libraAccount;
    }

}
