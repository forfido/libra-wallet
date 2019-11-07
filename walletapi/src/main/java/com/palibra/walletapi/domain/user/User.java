package com.palibra.walletapi.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.palibra.walletapi.domain.BaseEntity;
import com.palibra.walletapi.domain.auth.AuthProvider;
import com.palibra.walletapi.domain.libraaccount.LibraAccount;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USERS", uniqueConstraints = {
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

    @OneToMany(mappedBy = "user")
    private List<LibraAccount> libraAccounts = new ArrayList<LibraAccount>();


    private User(){
        this.emailVerified = false;
        this.enabled = true;
    }

    public static User createUser(String name, String email, String password, AuthProvider provider, String providerId, String imageUrl) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setProvider(provider);
        user.setProviderId(providerId);
        user.setImageUrl(imageUrl);

        return user;
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



    private void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    private void setProvider(AuthProvider provider) {
        this.provider = provider;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<LibraAccount> getAccounts() {
        return libraAccounts;
    }


}
