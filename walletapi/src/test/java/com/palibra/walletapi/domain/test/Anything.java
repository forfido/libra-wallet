package com.palibra.walletapi.domain.test;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class Anything {

    @Test
    public void StringToByte() {
        String str = "import 0x0.LibraAccount;\n" +
                "main (payee: address, amount: u64) {\n" +
                "  LibraAccount.pay_from_sender(move(payee), move(amount));\n" +
                "  return;\n" +
                "}";
        byte[] b = str.getBytes();

        String sut = "[76,73,66,82,65,86,77,10,1,0,7,1,74,0,0,0,4,0,0,0,3,78,0,0,0,6,0,0,0,13,84,0,0,0,6,0,0,0,14,90,0,0,0,6,0,0,0,5,96,0,0,0,41,0,0,0,4,137,0,0,0,32,0,0,0,8,169,0,0,0,15,0,0,0,0,0,0,1,0,2,0,1,3,0,2,0,2,4,2,0,3,2,4,2,3,0,6,60,83,69,76,70,62,12,76,105,98,114,97,65,99,99,111,117,110,116,4,109,97,105,110,15,112,97,121,95,102,114,111,109,95,115,101,110,100,101,114,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,2,0,4,0,12,0,12,1,19,1,1,2]";


        Assert.assertEquals(sut, Arrays.toString(b) );
    }
}
