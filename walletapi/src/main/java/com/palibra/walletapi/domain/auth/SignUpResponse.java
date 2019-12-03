package com.palibra.walletapi.domain.auth;

import com.palibra.walletapi.domain.libraaccount.Account;

public class SignUpResponse {
    private Long userId;
    private String email;
    private Account account;

    public SignUpResponse(Long userId, String email, Account account) {
        this.userId = userId;
        this.email = email;
        this.account = account;
    }

    public Long getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public Account getAccount() {
        return account;
    }
}
