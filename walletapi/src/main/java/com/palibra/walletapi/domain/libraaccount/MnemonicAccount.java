package com.palibra.walletapi.domain.libraaccount;

import dev.jlibra.KeyUtils;
import dev.jlibra.mnemonic.ExtendedPrivKey;

public class MnemonicAccount extends Account {

    private final ExtendedPrivKey extendedPrivKey;

    public MnemonicAccount(ExtendedPrivKey extendedPrivKey) {
        this.accountType = AccountType.MNEMONIC;
        this.extendedPrivKey = extendedPrivKey;
    }

    @Override
    public byte[] getAddress() {
        return KeyUtils.toByteArrayLibraAddress(extendedPrivKey.publicKey.getEncoded());
    }

    @Override
    public byte[] getPublicKey() {
        return extendedPrivKey.publicKey.getEncoded();
    }

    @Override
    public byte[] getPrivateKey() {
        return extendedPrivKey.privateKey.getEncoded();
    }
}
