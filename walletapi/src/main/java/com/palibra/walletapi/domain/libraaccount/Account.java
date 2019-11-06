package com.palibra.walletapi.domain.libraaccount;

public abstract class Account {

    protected AccountType accountType;

    public AccountType getAccountType() {
        return accountType;
    }

    public abstract byte[] getPublicKey();

    public abstract byte[] getPrivateKey();

    public abstract byte[] getAddress();

}
