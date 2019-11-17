package com.palibra.walletapi.controller;

import com.palibra.walletapi.controller.common.ApiResponse;
import com.palibra.walletapi.controller.common.TokenBaseController;
import com.palibra.walletapi.domain.libraaccount.LibraAccount;
import com.palibra.walletapi.domain.libraaccount.LibraAccountService;
import com.palibra.walletapi.domain.libraaccount.TransferRequest;
import com.palibra.walletapi.exception.ErrorHandlerException;
import dev.jlibra.spring.action.PeerToPeerTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/libra")
public class LibraAccountController extends TokenBaseController {

    @Autowired
    LibraAccountService libraAccountService;

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
