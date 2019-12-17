package com.palibra.walletapi.domain.user;

public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String imageUrl;
    private String libraAddress;

    public UserDto(Long id, String name, String email, String imageUrl, String libraAddress) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.imageUrl = imageUrl;
        this.libraAddress = libraAddress;
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
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLibraAddress() {
        return libraAddress;
    }

    public void setLibraAddress(String libraAddress) {
        this.libraAddress = libraAddress;
    }
}
