package com.palibra.walletapi.domain.libraaccount;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class LibraAccountServiceTest {

    @Autowired
    LibraAccountService libraAccountService;

    @Test
    public void findAccount() {
    }

    @Test
    public void testFindAccount() {
    }

    @Test
    public void getBalanceTest() {

        LibraAccount account = libraAccountService.findAccount(2L);

        Long balance = libraAccountService.getBalance(account.getLibraAddressToString());

        Assert.assertTrue(balance > 0);
    }
}
