package com.palibra.walletapi.domain.test;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.palibra.walletapi.domain.libraaccount.LibraAccount;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Entity
public class TempTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEST_ID")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Email
    @Column(nullable = false)
    private String email;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<LibraAccount> libraAccounts;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
