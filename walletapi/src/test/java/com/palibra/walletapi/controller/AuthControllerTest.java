package com.palibra.walletapi.controller;

import com.palibra.walletapi.controller.common.ApiResponse;
import com.palibra.walletapi.domain.auth.LoginRequest;
import com.palibra.walletapi.domain.auth.SignUpRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

import static com.palibra.walletapi.constants.config.BACKEND_API_DOMAIN;

public class AuthControllerTest {

    private RestTemplate restTemplate;

    @Before
    public void setUp() throws Exception {
        restTemplate = new RestTemplate();
    }

    @Test
    public void authenticateUserTest() throws URISyntaxException {

        final String baseUrl = BACKEND_API_DOMAIN + "/auth/login";
        URI uri = new URI(baseUrl);

        LoginRequest param = new LoginRequest();
        param.setEmail("ccami@palibra.net");
        param.setPassword("123123");

        HttpEntity<LoginRequest> request = new HttpEntity<>(param);

        ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);

        //Verify request succeed
        Assert.assertEquals(200, result.getStatusCodeValue());

        //Assert.assertEquals("", result.getBody());
    }

    @Test
    public void registerUserTest() throws URISyntaxException {
        //Given
        final String baseUrl = BACKEND_API_DOMAIN + "/auth/signUp";

        SignUpRequest param = new SignUpRequest();
        param.setEmail("ahin@palibra.net");
        param.setPassword("123123");
        param.setName("아인");

        HttpEntity<SignUpRequest> request = new HttpEntity<>(param);

        //When
        ResponseEntity<ApiResponse> result = restTemplate.exchange(baseUrl, HttpMethod.POST, request, ApiResponse.class);

        //Then
        Assert.assertEquals(200, result.getStatusCodeValue());

        Assert.assertEquals("c94fca612ec8baceae50e2592ef81da9c1a31d41fa9c9527c29a0a7371438120", Objects.requireNonNull(result.getBody()).getContents());
    }
}
