package com.palibra.walletapi.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;

import static com.palibra.walletapi.constants.config.AUTHORIZATION_TOKEN;
import static com.palibra.walletapi.constants.config.BACKEND_API_DOMAIN;

public class UserControllerTest
{
    private RestTemplate restTemplate;
    private HttpHeaders headers;

    @Before
    public void setUp() throws Exception {
        restTemplate = new RestTemplate();
        headers = new HttpHeaders();
        headers.set("Authorization", AUTHORIZATION_TOKEN);
    }

    @Test
    public void testGetCurrentUser() throws URISyntaxException {
        //Given
        long id = 1L;
        final String baseUrl = BACKEND_API_DOMAIN + "/user/me?id=" + id;

        //When
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<String> result = restTemplate.exchange(baseUrl, HttpMethod.GET, request, String.class);

        //Then
        Assert.assertEquals(200, result.getStatusCodeValue());
    }
}