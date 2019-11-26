package com.palibra.walletapi.domain.libraaccount.payload;

public class LibraBalance {
    private Long libra;
    private Long libraMicro;

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
}
