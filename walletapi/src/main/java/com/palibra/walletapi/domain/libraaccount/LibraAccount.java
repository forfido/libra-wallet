package com.palibra.walletapi.domain.libraaccount;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.palibra.walletapi.domain.user.User;

import javax.persistence.*;

@Entity
public class LibraAccount {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNT_ID")
    private Long id;

    private String name;

    @JsonIgnore
    private byte[] privateKey;

    private byte[] publicKey;
    private String libraAddress;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    @JsonIgnore
    private User user;

    private LibraAccount(){}

    //생성자 메서드
    public static LibraAccount createAccount(String name, String libraAddress, byte[] privateKey, byte[] publicKey, User user) {
        LibraAccount account = new LibraAccount();
        account.setName(name);
        account.setLibraAddress(libraAddress);
        account.setPrivateKey(privateKey);
        account.setPublicKey(publicKey);
        account.setUser(user);

        return account;
    }

    //연관관계 메서드
    private void setUser(User user) {
        if (this.user != null) {
            this.user.getAccounts().remove(this);
        }
        this.user = user;
        user.getAccounts().add(this);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLibraAddressToString() {
        return libraAddress;
    }

    public byte[] getPrivateKey() {
        return privateKey;
    }

    public byte[] getPublicKey() {
        return publicKey;
    }

    public User getUser(){
        return user;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setLibraAddress(String libraAddress) {
        this.libraAddress = libraAddress;
    }

    public void setPrivateKey(byte[] privateKey) {
        this.privateKey = privateKey;
    }

    public void setPublicKey(byte[] publicKey) {
        this.publicKey = publicKey;
    }
}
