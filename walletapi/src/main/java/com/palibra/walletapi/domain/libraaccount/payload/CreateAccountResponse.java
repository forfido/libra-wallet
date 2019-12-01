package com.palibra.walletapi.domain.libraaccount.payload;

public class CreateAccountResponse {
    private Long userId;
    private byte[] libraAddress;

    public CreateAccountResponse(Long userId, byte[] libraAddress) {
        this.userId = userId;
        this.libraAddress = libraAddress;
    }

    public Long getUserId() {
        return userId;
    }

    public byte[] getLibraAddress() {
        return libraAddress;
    }
}
