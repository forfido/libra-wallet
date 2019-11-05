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

    @OneToOne
    @JoinColumn(name = "WALLET_ID")
    private Wallet wallet;


    private User(){ }

    private User(String name, String email, String password, AuthProvider provider, String providerId, String imageUrl) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.provider = provider;
        this.providerId = providerId;
        this.imageUrl = imageUrl;

        this.emailVerified = false;
        this.enabled = true;
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

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setWallet(Wallet wallet){ this.wallet = wallet; }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static class UserBuilder {
        private String name;
        private String email;
        private String password;
        private AuthProvider provider;
        private String providerId;
        private String imageUrl;

        public UserBuilder setName(final String name) {
            this.name = name;
            return this;
        }
        public UserBuilder setEmail(final String email) {
            this.email = email;
            return this;
        }
        public UserBuilder setPassword(final String password) {
            this.password = password;
            return this;
        }
        public UserBuilder setProviderId(final String providerId) {
            this.providerId = providerId;
            return this;
        }
        public UserBuilder setProvider(final AuthProvider provider) {
            this.provider = provider;
            return this;
        }
        public UserBuilder setImageUrl(final String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public User build() {
            return new User(name, email, password, provider, providerId, imageUrl);
        }
    }
}
