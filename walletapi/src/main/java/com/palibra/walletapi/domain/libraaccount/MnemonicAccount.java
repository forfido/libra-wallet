package com.palibra.walletapi.domain.libraaccount;

import dev.jlibra.mnemonic.ExtendedPrivKey;

public class MnemonicAccount extends Account {

    private final ExtendedPrivKey extendedPrivKey;

    public MnemonicAccount(ExtendedPrivKey extendedPrivKey) {
        this.extendedPrivKey = extendedPrivKey;
    }

    @Override
    public String getAddress() {
        //TODO : not yet completed
        return "";
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
