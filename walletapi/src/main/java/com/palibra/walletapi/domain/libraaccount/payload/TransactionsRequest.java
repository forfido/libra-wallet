package com.palibra.walletapi.domain.libraaccount.payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class TransactionsRequest {
    private String module;

    private String action;

    private String address;

    private String sort;

    public TransactionsRequest() {
        this.module  = "account";
        this.action  = "txlist";
        this.address = "";
        this.sort    = "asc";
    }
}
