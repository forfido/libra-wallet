package com.palibra.walletapi.domain.account;

import com.palibra.walletapi.domain.user.User;
import com.palibra.walletapi.domain.wallet.Wallet;

import javax.persistence.*;

@Entity
public class LibraAccount {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNT_ID")
    private Long id;

    private String name;
    private String currency;

    private byte[] libraAddress;
    private byte[] privateKey;
    private byte[] publicKey;

    @ManyToOne
    @JoinColumn(name = "WALLET_ID")
    private Wallet wallet;

    private LibraAccount(){}

    public static LibraAccount createAccount(String name, String currency, byte[] libraAddress, byte[] privateKey, byte[] publicKey, Wallet wallet) {
        LibraAccount account = new LibraAccount();
        account.setName(name);
        account.setCurrency(currency);
        account.setLibraAddress(libraAddress);
        account.setPrivateKey(privateKey);
        account.setPublicKey(publicKey);
        account.setWallet(wallet);

        return account;
    }

    //연관관계 메서드
    private void setWallet(Wallet wallet) {
        if (this.wallet != null) {
            this.wallet.getAccounts().remove(this);
        }
        this.wallet = wallet;
        wallet.getAccounts().add(this);
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

    public byte[] getLibraAddress() {
        return libraAddress;
    }

    public byte[] getPrivateKey() {
        return privateKey;
    }

    public byte[] getPublicKey() {
        return publicKey;
    }

    public Wallet getWallet(){
        return wallet;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setLibraAddress(byte[] libraAddress) {
        this.libraAddress = libraAddress;
    }

    public void setPrivateKey(byte[] privateKey) {
        this.privateKey = privateKey;
    }

    public void setPublicKey(byte[] publicKey) {
        this.publicKey = publicKey;
    }
}
