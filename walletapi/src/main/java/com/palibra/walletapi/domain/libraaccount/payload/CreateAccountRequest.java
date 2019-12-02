package com.palibra.walletapi.domain.libraaccount.payload;

public class CreateAccountRequest {
    private String libraAccountName;

    public CreateAccountRequest(String libraAccountName) {
        this.libraAccountName = libraAccountName;
   }

    public String getLibraAccountName() {
        return libraAccountName;
    }
}
