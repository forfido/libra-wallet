package com.palibra.walletapi.domain.user;

import com.palibra.walletapi.domain.libraaccount.LibraAccount;

public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String imageUrl;
    private LibraAccount libraAccount;

    public UserDto(Long id, String name, String email, String imageUrl, LibraAccount libraAccount) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.imageUrl = imageUrl;
        this.libraAccount = libraAccount;
    }

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

    public String getImageUrl() {
        if (imageUrl == null || imageUrl.isEmpty())
            return "https://ui-avatars.com/api/?name=" + name;

        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LibraAccount getLibraAddress() {
        return libraAccount;
    }

    public void setLibraAddress(LibraAccount libraAccount) {
        this.libraAccount = libraAccount;
    }
}
