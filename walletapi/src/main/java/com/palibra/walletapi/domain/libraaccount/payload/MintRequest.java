package com.palibra.walletapi.domain.libraaccount.payload;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class MintRequest {
    @NotNull
    private String libraAccountName;
    @Min(0)
    private Long amount;

    public MintRequest(String libraAccountName, Long amount) {
        this.libraAccountName = libraAccountName;
        this.amount = amount;
    }

    public String getLibraAccountName() {
        return libraAccountName;
    }

    public Long getAmount() {
        return amount;
    }
}
