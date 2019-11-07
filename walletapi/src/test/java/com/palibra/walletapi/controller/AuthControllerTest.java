package com.palibra.walletapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.palibra.walletapi.controller.common.ApiResponse;
import com.palibra.walletapi.domain.auth.LoginRequest;
import com.palibra.walletapi.domain.auth.SignUpRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

import static com.palibra.walletapi.constants.config.AUTHORIZATION_TOKEN;
import static com.palibra.walletapi.constants.config.BACKEND_API_DOMAIN;

public class AuthControllerTest {

    private RestTemplate restTemplate;
    private HttpHeaders headers;

    @Autowired
    ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
        restTemplate = new RestTemplate();
        headers = new HttpHeaders();
        headers.set("Authorization", AUTHORIZATION_TOKEN);
    }

    @Test
    public void LocalLoginTest() throws URISyntaxException {

        final String baseUrl = BACKEND_API_DOMAIN + "/auth/login";
        URI uri = new URI(baseUrl);

        LoginRequest param = new LoginRequest();
        param.setEmail("ccami@palibra.net");
        param.setPassword("123123");

        HttpEntity<LoginRequest> request = new HttpEntity<>(param);

        ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);

        //Verify request succeed
        Assert.assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    public void registerUserTest() {
        //Given
        final String baseUrl = BACKEND_API_DOMAIN + "/auth/signUp";

        SignUpRequest param = new SignUpRequest();
        param.setEmail("ahin131005@palibra.net");
        param.setPassword("123123");
        param.setName("아인");

        HttpEntity<SignUpRequest> request = new HttpEntity<>(param, headers);

        //When
        ResponseEntity<ApiResponse> result = restTemplate.exchange(baseUrl, HttpMethod.POST, request, ApiResponse.class);

        //Then
        Assert.assertEquals(200, result.getStatusCodeValue());

        Assert.assertTrue(Objects.requireNonNull(result.getBody()).isSuccess());
    }
}
