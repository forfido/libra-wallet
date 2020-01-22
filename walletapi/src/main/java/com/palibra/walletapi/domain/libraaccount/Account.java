package com.palibra.walletapi.domain.libraaccount;

public abstract class Account {

//    protected AccountAddress accountAddress;
//
//    public AccountAddress getAccountAddress() {
//        return accountAddress;
//    }

    public abstract byte[] getPublicKey();

    public abstract byte[] getPrivateKey();

    public abstract String getAddress();

}
