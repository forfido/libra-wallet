package com.palibra.walletapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.palibra.walletapi.controller.common.ApiResponse;
import com.palibra.walletapi.domain.libraaccount.payload.TransferRequest;
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

import static com.palibra.walletapi.constants.config.*;

public class LibraAccountControllerTest {

    private RestTemplate restTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
        restTemplate = new RestTemplate();

    }

    @Test
    public void transferLibra() {
        final String baseUrl = BACKEND_API_DOMAIN + "/libra/transfer";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", AUTHORIZATION_AHIN_TOKEN);

        TransferRequest param = new TransferRequest();
        param.setReceiverUserId(2L);
        param.setAmount(new BigInteger("150"));

        HttpEntity<TransferRequest> request = new HttpEntity<>(param, headers);

        //When
        ResponseEntity<ApiResponse> result = restTemplate.exchange(baseUrl, HttpMethod.POST, request, ApiResponse.class);

        //Then
        Assert.assertEquals(200, result.getStatusCodeValue());
    }

//    @Test
//    public getBalanceTest() {
//
//    }
}
