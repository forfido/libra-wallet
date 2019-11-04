package com.palibra.walletapi.domain.wallet;

import com.palibra.walletapi.domain.account.Account;
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
    private List<Account> accounts = new ArrayList<Account>();


    public Wallet() {
        this.accounts = new ArrayList<>();
    }
}
