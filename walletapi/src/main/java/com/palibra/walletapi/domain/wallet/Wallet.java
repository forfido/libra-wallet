package com.palibra.walletapi.domain.wallet;

import com.palibra.walletapi.domain.account.LibraAccount;
import com.palibra.walletapi.domain.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Wallet {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WALLET_ID")
    private Long id;

    @OneToOne(mappedBy = "wallet")
    private User user;

    @OneToMany(mappedBy = "wallet")
    private List<LibraAccount> accounts = new ArrayList<LibraAccount>();

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public List<LibraAccount> getAccounts() {
        return accounts;
    }

    public Wallet(User user) {
        this.user = user;
        this.accounts = new ArrayList<>();
    }
}
