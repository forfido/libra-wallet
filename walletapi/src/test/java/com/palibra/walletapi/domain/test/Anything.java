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

        //이방벅으로 하는 것이 아님
        //Move 컴파일러를 통해 bytecode를 생성해야 함
        String sut = "[105, 109, 112, 111, 114, 116, 32, 48, 120, 48, 46, 76, 105, 98, 114, 97, 65, 99, 99, 111, 117, 110, 116, 59, 10, 109, 97, 105, 110, 32, 40, 112, 97, 121, 101, 101, 58, 32, 97, 100, 100, 114, 101, 115, 115, 44, 32, 97, 109, 111, 117, 110, 116, 58, 32, 117, 54, 52, 41, 32, 123, 10, 32, 32, 76, 105, 98, 114, 97, 65, 99, 99, 111, 117, 110, 116, 46, 112, 97, 121, 95, 102, 114, 111, 109, 95, 115, 101, 110, 100, 101, 114, 40, 109, 111, 118, 101, 40, 112, 97, 121, 101, 101, 41, 44, 32, 109, 111, 118, 101, 40, 97, 109, 111, 117, 110, 116, 41, 41, 59, 10, 32, 32, 114, 101, 116, 117, 114, 110, 59, 10, 125]";


        Assert.assertEquals(sut, Arrays.toString(b) );
    }
}
