package com.palibra.walletapi.controller;

import com.palibra.walletapi.controller.common.ApiResponse;
import com.palibra.walletapi.controller.common.TokenBaseController;
import com.palibra.walletapi.domain.libraaccount.LibraAccount;
import com.palibra.walletapi.domain.libraaccount.LibraAccountService;
import com.palibra.walletapi.domain.libraaccount.TransferRequest;
import com.palibra.walletapi.exception.ErrorHandlerException;
import com.palibra.walletapi.util.ZXingHelper;
import dev.jlibra.spring.action.PeerToPeerTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;


@RestController
@RequestMapping("/libra")
public class LibraAccountController extends TokenBaseController {

    @Autowired
    LibraAccountService libraAccountService;

    @GetMapping("/account")
    public byte[] getAccount() throws IOException {

        LibraAccount libraAccount = libraAccountService.findAccount(getAuthedUserInfo().getId());

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(Objects.requireNonNull(ZXingHelper.getQRCodeImage(libraAccount.getLibraAddressToString(), 200, 200)));

        return outputStream.toByteArray();
    }

    @GetMapping("/balance")
    public ResponseEntity<?> getBalance() {

        LibraAccount libraAccount = libraAccountService.findAccount(getAuthedUserInfo().getId());

        Long balance = libraAccountService.getBalance(libraAccount.getLibraAddressToString());
        return ApiResponse.Success(balance);
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transferLibra(@Valid @RequestBody TransferRequest transferRequest) {
        Long senderId = getAuthedUserInfo().getId();
        if (senderId.equals(transferRequest.getReceiverUserId())) {
            throw new ErrorHandlerException("Not available transfer to yourself");
        }
        PeerToPeerTransfer.PeerToPeerTransferReceipt.Status status = libraAccountService.transfer( senderId, transferRequest);

        return ApiResponse.Success(status);
    }


}
