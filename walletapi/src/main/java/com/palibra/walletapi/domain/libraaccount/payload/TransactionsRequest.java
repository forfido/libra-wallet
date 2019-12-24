package com.palibra.walletapi.domain.libraaccount.payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class TransactionsRequest {
    @NotNull
    private String module;

    @NotNull
    private String action;

    @NotNull
    private String address;

    @NotNull
    private String sort;

    public TransactionsRequest() {
        this.module = "account";
        this.action = "txlist";
        this.address = "d1ecca696749316a1ebe71ecee2012ae004cd4427d0a043fcf700689924309b2";
        this.sort = "asc";
    }
}
