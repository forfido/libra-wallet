package com.palibra.walletapi.model.supperkey;

import java.io.Serializable;
import java.util.Objects;

import com.palibra.walletapi.model.enums.Currency;

public class WalletId implements Serializable {
    
    private static final long serialVersionUID = -7670210539694722964L;
    private Long userId;
    private Currency currency;

    public WalletId() {
    }

    public WalletId(Long userId, Currency currency) {
        this.userId = userId;
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof WalletId)) {
            return false;
        }
        WalletId walletId = (WalletId) o;
        return Objects.equals(userId, walletId.userId) && Objects.equals(currency, walletId.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, currency);
    }
    
}