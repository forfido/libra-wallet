package com.palibra.walletapi.domain.libraaccount.payload;

import javax.validation.constraints.Min;

public class MintRequest {
    @Min(0)
    private Long amount;

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getAmount() {
        return amount;
    }
}
