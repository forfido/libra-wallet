package com.palibra.walletapi.controller;

import com.palibra.walletapi.controller.common.ApiResponse;
import com.palibra.walletapi.domain.transaction.TransactionService;
import com.palibra.walletapi.domain.transaction.TransferRequest;
import dev.jlibra.spring.action.PeerToPeerTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/transfer/libra")
    public ResponseEntity<?> transferLibra(@Valid @RequestBody TransferRequest transferRequest) {

        PeerToPeerTransfer.PeerToPeerTransferReceipt.Status status = transactionService.transfer(transferRequest.getSenderUserId(), transferRequest.getReceiverUserId(), transferRequest.getAmount());

        return ApiResponse.Success(status);
    }
}
