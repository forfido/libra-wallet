package com.palibra.walletapi.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.palibra.walletapi.domain.BaseEntity;
import com.palibra.walletapi.domain.auth.AuthProvider;
import com.palibra.walletapi.domain.wallet.Wallet;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
//@EqualsAndHashCode(callSuper = false)
@Table(name = "USERS", uniqueConstraints = {
    //@UniqueConstraint(columnNames = {"username"}),
    @UniqueConstraint(columnNames = "email")
})
public class User extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Email
    @Column(nullable = false)
    private String email;

    private String imageUrl;

    @Column(nullable = false)
    private Boolean emailVerified;

    @JsonIgnore
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    private String providerId;

    private Boolean enabled;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "WALLET_ID")
    private Wallet wallet;


    private User(){
        this.emailVerified = false;
        this.enabled = true;
    }

    public static User createUser(String name, String email, String password, AuthProvider provider, String providerId, String imageUrl, Wallet wallet) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setProvider(provider);
        user.setProviderId(providerId);
        user.setImageUrl(imageUrl);
        user.setWallet(wallet);

        return user;
    }

    private void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    private void setProvider(AuthProvider provider) {
        this.provider = provider;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public String getPassword() {
        return password;
    }

    public AuthProvider getProvider() {
        return provider;
    }

    public String getProviderId() {
        return providerId;
    }

    public Boolean IsEnabled() {
        return enabled;
    }

    public Wallet getWallet() { return wallet; }

    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public void setWallet(Wallet wallet){ this.wallet = wallet; }

}
