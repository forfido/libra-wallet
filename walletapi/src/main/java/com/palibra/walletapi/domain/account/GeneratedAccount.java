package com.palibra.walletapi.domain.account;

import dev.jlibra.KeyUtils;

import java.security.PrivateKey;
import java.security.PublicKey;

public class GeneratedAccount extends Account {

    private final PrivateKey privateKey;
    private final PublicKey publicKey;

    public GeneratedAccount(PrivateKey privateKey, PublicKey publicKey) {
        this.accountType = AccountType.GENERATED;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    @Override
    public byte[] getAddress() {
        return KeyUtils.toByteArrayLibraAddress(publicKey.getEncoded());
    }

    @Override
    public byte[] getPrivateKey() {
        return privateKey.getEncoded();
    }

    @Override
    public byte[] getPublicKey() {
        return publicKey.getEncoded();
    }
}
