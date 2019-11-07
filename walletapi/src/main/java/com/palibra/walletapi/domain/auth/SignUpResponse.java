package com.palibra.walletapi.domain.auth;

import com.palibra.walletapi.domain.libraaccount.Account;

public class SignUpResponse {
    private Long userId;

    private Account libraAccount;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Account getLibraAccount() {
        return libraAccount;
    }

    public void setLibraAccount(Account libraAccount) {
        this.libraAccount = libraAccount;
    }



}
