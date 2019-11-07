package com.palibra.walletapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.palibra.walletapi.controller.common.ApiResponse;
import com.palibra.walletapi.domain.auth.SignUpRequest;
import com.palibra.walletapi.domain.auth.SignUpResponse;
import com.palibra.walletapi.domain.transaction.TransferRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;

import static com.palibra.walletapi.constants.config.AUTHORIZATION_TOKEN;
import static com.palibra.walletapi.constants.config.BACKEND_API_DOMAIN;

public class TransactionControllerTest {

    private RestTemplate restTemplate;
    private HttpHeaders headers;

    private Long senderId;
    private Long receiverId;

    @Autowired
    ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
        restTemplate = new RestTemplate();
        headers = new HttpHeaders();
        headers.set("Authorization", AUTHORIZATION_TOKEN);

        final String baseUrl = BACKEND_API_DOMAIN + "/auth/signUp";

        SignUpRequest param = new SignUpRequest();
        param.setEmail("ahin994@palibra.net");
        param.setPassword("123123");
        param.setName("아인");

        HttpEntity<SignUpRequest> request = new HttpEntity<>(param, headers);
        ResponseEntity<ApiResponse> result = restTemplate.exchange(baseUrl, HttpMethod.POST, request, ApiResponse.class);
        SignUpResponse signUpResponse = objectMapper.readValue((byte[]) result.getBody().getContents(), SignUpResponse.class);
        senderId = signUpResponse.getUserId();

        Assert.assertEquals(200, result.getStatusCodeValue());

        param.setEmail("ehwan881@palibra.net");
        param.setPassword("123123");
        param.setName("이환");

        request = new HttpEntity<>(param, headers);
        result = restTemplate.exchange(baseUrl, HttpMethod.POST, request, ApiResponse.class);
        signUpResponse = objectMapper.readValue((byte[]) result.getBody().getContents(), SignUpResponse.class);

        receiverId = signUpResponse.getUserId();

        Assert.assertEquals(200, result.getStatusCodeValue());

    }

    @Test
    public void transferLibra() {
        final String baseUrl = BACKEND_API_DOMAIN + "/transaction/transfer/libra";

        TransferRequest param = new TransferRequest();
        param.setSenderUserId(senderId);
        param.setReceiverUserId(receiverId);
        param.setAmount(new BigInteger("400"));

        HttpEntity<TransferRequest> request = new HttpEntity<>(param);

        //When
        ResponseEntity<ApiResponse> result = restTemplate.exchange(baseUrl, HttpMethod.POST, request, ApiResponse.class);

        //Then
        Assert.assertEquals(200, result.getStatusCodeValue());


    }
}
