package com.palibra.walletapi.domain.libraaccount.payload;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigInteger;

public class TransferRequest {

    @NotNull
    private String libraAddress;

    @Positive
    private BigInteger amount;


    public String getLibraAddress() {
        return libraAddress;
    }

    public void setLibraAddress(String libraAddress) {
        this.libraAddress = libraAddress;
    }

    public BigInteger getAmount() {
        return amount;
    }

    public void setAmount(BigInteger amount) {
        this.amount = amount;
    }

}
