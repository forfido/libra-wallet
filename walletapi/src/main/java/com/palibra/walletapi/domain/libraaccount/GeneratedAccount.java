package com.palibra.walletapi.domain.libraaccount;

import dev.jlibra.AccountAddress;
import dev.jlibra.serialization.ByteSequence;

import java.security.PrivateKey;
import java.security.PublicKey;

public class GeneratedAccount extends Account {

    private final AccountAddress accountAddress;
    private final PrivateKey privateKey;
    private final PublicKey publicKey;

    public GeneratedAccount(PrivateKey privateKey, PublicKey publicKey) {
        this.accountAddress = AccountAddress.ofPublicKey(publicKey);
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    @Override
    public String getAddress() {
        return accountAddress.getByteSequence().toString();
    }

    @Override
    public byte[] getPrivateKey() {
        return ByteSequence.from(privateKey.getEncoded()).toArray();
    }

    @Override
    public byte[] getPublicKey() {
        return ByteSequence.from(publicKey.getEncoded()).toArray();
    }
}
