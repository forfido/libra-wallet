package com.palibra.walletapi.domain.account;

import com.palibra.walletapi.domain.user.User;

import javax.persistence.*;

@Entity
public class Account {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String currency;

    private String libraAddress;
    private String privateKey;
    private String publicKey;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    private Account(){}

    public static Account createAccount(String name, String currency, String libraAddress, String privateKey, String publicKey, User user) {
        Account account = new Account();
        account.setName(name);
        account.setCurrency(currency);
        account.setLibraAddress(libraAddress);
        account.setPrivateKey(privateKey);
        account.setPublicKey(publicKey);
        account.setUser(user);

        return account;
    }

    //연관관계 메서드
    public void setUser(User user) {
        this.user = user;
        user.getLibraAccount().add(this);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCurrency() {
        return currency;
    }

    public String getLibraAddress() {
        return libraAddress;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public User getUser(){
        return user;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setLibraAddress(String libraAddress) {
        this.libraAddress = libraAddress;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
}
