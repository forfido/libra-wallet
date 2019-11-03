package com.palibra.walletapi.controller;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class UserControllerTest
{
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testGetCurrentUser() throws ClientProtocolException, IOException
    {
        //Given
        long id = 1L;
        HttpUriRequest request = new HttpGet("http://localhost:8090/user/me?id=" + id);

        //When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        //Then
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
    }

    @Test
    public void testSetPassword() {
        String pass = passwordEncoder.encode("123123");

        assertThat(pass, equalTo("123"));
    }
}