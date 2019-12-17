package com.palibra.walletapi.domain.libraaccount.payload;

public class LibraBalance {
    private Long libra;
    private Long libraMicro;
    private String libraAddress;
    private Long userId;

    public Long getLibra() {
        return libra;
    }

    public void setLibra(Long libra) {
        this.libra = libra;
    }

    public Long getLibraMicro() {
        return libraMicro;
    }

    public void setLibraMicro(Long libraMicro) {
        this.libraMicro = libraMicro;
    }

    public String getLibraAddress() {
        return libraAddress;
    }

    public void setLibraAddress(String libraAddress) {
        this.libraAddress = libraAddress;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
