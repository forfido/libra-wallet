package com.palibra.walletapi.controller;

import com.palibra.walletapi.controller.common.ApiResponse;
import com.palibra.walletapi.controller.common.TokenBaseController;
import com.palibra.walletapi.domain.libraaccount.Account;
import com.palibra.walletapi.domain.libraaccount.LibraAccount;
import com.palibra.walletapi.domain.libraaccount.LibraAccountService;
import com.palibra.walletapi.domain.libraaccount.payload.*;
import com.palibra.walletapi.domain.user.User;
import com.palibra.walletapi.domain.user.UserService;
import com.palibra.walletapi.exception.ErrorHandlerException;
import com.palibra.walletapi.util.ZXingHelper;
import dev.jlibra.spring.action.PeerToPeerTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/libra")
public class LibraAccountController extends TokenBaseController {

    @Autowired
    LibraAccountService libraAccountService;

    @Autowired
    private UserService userService;

    @GetMapping("/account/{accountName}")
    public byte[] getAccount(@PathVariable String accountName) throws IOException {

        LibraAccount libraAccount = libraAccountService.findAccount(getAuthedUserInfo().getId(), accountName);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(Objects.requireNonNull(ZXingHelper.getQRCodeImage(libraAccount.getLibraAddressToString(), 200, 200)));

        return outputStream.toByteArray();
    }

    @GetMapping("/account/list")
    public ResponseEntity<?> getAccount() {

        List<LibraAccount> libraAccounts = libraAccountService.findAccounts(getAuthedUserInfo().getId());

        return ApiResponse.Success(libraAccounts);
    }

    @PostMapping("/account")
    public ResponseEntity<?> CreateAccount(@Valid @RequestBody CreateAccountRequest createAccountRequest) {

        User user = userService.getUserInfo(getAuthedUserInfo().getId());

        Account account = libraAccountService.createLibraUserAccount(user, createAccountRequest.getLibraAccountName());
        CreateAccountResponse createAccountResponse = new CreateAccountResponse(user.getId(), account.getAddress());

        return ApiResponse.Success(createAccountResponse);
    }

    @GetMapping("/balance/{accountName}")
    public ResponseEntity<?> getBalance(@PathVariable String accountName) {

        LibraAccount libraAccount = libraAccountService.findAccount(getAuthedUserInfo().getId(), accountName);

        LibraBalance balance = libraAccountService.getBalance(libraAccount.getLibraAddressToString());
        return ApiResponse.Success(balance);
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transferLibra(@Valid @RequestBody TransferRequest transferRequest) {
        Long senderId = getAuthedUserInfo().getId();
        if (senderId.equals(transferRequest.getReceiverUserId())) {
            throw new ErrorHandlerException("Not available transfer to yourself");
        }
        PeerToPeerTransfer.PeerToPeerTransferReceipt.Status status = libraAccountService.transferToMember( senderId, transferRequest);

        return ApiResponse.Success(status);
    }

    @PostMapping("/mint")
    public ResponseEntity<?> mintLibra(@Valid @RequestBody MintRequest mintRequest) {

        LibraAccount libraAccount = libraAccountService.findAccount(getAuthedUserInfo().getId(), mintRequest.getLibraAccountName());

        Long responseAmount = libraAccountService.mint(libraAccount.getLibraAddressToString(), mintRequest.getAmount());

        return ApiResponse.Success(responseAmount);
    }

}
