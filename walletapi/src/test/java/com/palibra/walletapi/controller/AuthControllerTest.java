package com.palibra.walletapi.controller;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.palibra.walletapi.constants.config.BACKEND_API_DOMAIN;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class AuthControllerTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void authenticateUser() throws IOException {
        //Given
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost request = new HttpPost(BACKEND_API_DOMAIN + "/auth/login");

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("username", "John"));
        params.add(new BasicNameValuePair("password", "pass"));
        request.setEntity(new UrlEncodedFormEntity(params));

        //When
        CloseableHttpResponse httpResponse = client.execute(request);

        //Then
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));

        client.close();
    }

    @Test
    public void registerUser() {
    }
}
