package com.palibra.walletapi.domain.libraaccount;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.palibra.walletapi.domain.auth.SignUpRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.security.Security;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LibraAccountServiceTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    LibraAccountService libraAccountService;

    @Autowired
    ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

        SignUpRequest param = new SignUpRequest();
        param.setEmail("ahin@palibra.net");
        param.setPassword("123123");
        param.setName("아인");

        mockMvc.perform(post("/auth/signUp")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(param)))
                .andDo(print())
                .andExpect(jsonPath("success").exists());
    }

    @Test
    public void getBalanceTest() {

        LibraAccount account = libraAccountService.findAccount(1L);

        Long balance = libraAccountService.getBalance(account.getLibraAddressToString());

        Assert.assertTrue(balance > 0);
    }
}
