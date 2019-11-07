package com.palibra.walletapi.domain.transaction;

import com.palibra.walletapi.domain.libraaccount.LibraAccount;
import com.palibra.walletapi.domain.libraaccount.LibraAccountService;
import dev.jlibra.KeyUtils;
import dev.jlibra.spring.action.PeerToPeerTransfer;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.PrivateKey;
import java.security.PublicKey;

@Service
public class TransactionService {

    @Autowired
    private LibraAccountService libraAccountService;

    @Autowired
    private PeerToPeerTransfer peerToPeerTransfer;

    public PeerToPeerTransfer.PeerToPeerTransferReceipt.Status transfer(Long senderId, Long receiverId, BigInteger amount) {
        BigInteger gasUnitPrice = new BigInteger("-1");
        BigInteger maxGasAmount = new BigInteger("-1");

        LibraAccount senderAccount = libraAccountService.findAccount(senderId);
        LibraAccount receiverAccount = libraAccountService.findAccount(receiverId);

        String receiverAddress = Hex.toHexString(receiverAccount.getLibraAddress());
        PublicKey publicKey = KeyUtils.publicKeyFromHexString(new String(Hex.encode(senderAccount.getPublicKey())));
        PrivateKey privateKey = KeyUtils.privateKeyFromHexString(new String(Hex.encode(senderAccount.getPrivateKey())));
        PeerToPeerTransfer.PeerToPeerTransferReceipt receipt = peerToPeerTransfer.transferFunds(receiverAddress, amount.longValue() * 1_000_000, publicKey, privateKey, gasUnitPrice.longValue(), maxGasAmount.longValue());
        return receipt.getStatus();
    }
}
