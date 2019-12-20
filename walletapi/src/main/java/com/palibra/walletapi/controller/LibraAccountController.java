package com.palibra.walletapi.controller;

import com.palibra.walletapi.controller.common.ApiResponse;
import com.palibra.walletapi.controller.common.TokenBaseController;
import com.palibra.walletapi.domain.libraaccount.LibraAccount;
import com.palibra.walletapi.domain.libraaccount.LibraAccountService;
import com.palibra.walletapi.domain.libraaccount.payload.LibraBalance;
import com.palibra.walletapi.domain.libraaccount.payload.MintRequest;
import com.palibra.walletapi.domain.libraaccount.payload.TransferRequest;
import com.palibra.walletapi.domain.user.UserService;
import com.palibra.walletapi.exception.ErrorHandlerException;
import dev.jlibra.admissioncontrol.transaction.result.LibraTransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/libra")
public class LibraAccountController extends TokenBaseController {

    @Autowired
    LibraAccountService libraAccountService;

    @Autowired
    UserService userService;

    @GetMapping("/account")
    public ResponseEntity<?> getAccount() {

        LibraAccount libraAccount = libraAccountService.findAccount(getAuthedUserInfo().getId());

        return ApiResponse.Success(libraAccount.getLibraAddressToString());
    }

    /*
    리브라 계좌 생성 기능은 외부로 노출 시키지 않음 (v0.1)

    @PostMapping("/account")
    public ResponseEntity<?> CreateAccount(@Valid @RequestBody CreateAccountRequest createAccountRequest) {

        User user = userService.getUserInfo(getAuthedUserInfo().getId());

        Account account = libraAccountService.createLibraUserAccount(user, createAccountRequest.getLibraAccountName());
        CreateAccountResponse createAccountResponse = new CreateAccountResponse(user.getId(), account.getAddress());

        return ApiResponse.Success(createAccountResponse);
    }

    @PutMapping("/account/{userId}")
    public ResponseEntity<?> CreateAccount(@Valid @PathVariable Long userId) {

        User user = userService.getUserInfo(userId);

        Account account = libraAccountService.createLibraUserAccount(user);
        CreateAccountResponse createAccountResponse = new CreateAccountResponse(user.getId(), account.getAddress());

        return ApiResponse.Success(createAccountResponse);
    }
    */



    @GetMapping("/balance")
    public ResponseEntity<?> getBalance() {

        LibraAccount libraAccount = libraAccountService.findAccount(getAuthedUserInfo().getId());

        LibraBalance balance = libraAccountService.getBalance(libraAccount.getLibraAddressToString());
        balance.setUserId(getAuthedUserInfo().getId());
        return ApiResponse.Success(balance);
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transferLibra(@Valid @RequestBody TransferRequest transferRequest) {
        String result;
        Long senderId = getAuthedUserInfo().getId();

        try {
            result = libraAccountService.transferToMember( senderId, transferRequest);
        } catch(LibraTransactionException ex) {
            throw new ErrorHandlerException("Not available transfer : " + ex.getMessage());
        }

        LibraAccount libraAccount = libraAccountService.findAccount(getAuthedUserInfo().getId());

        LibraBalance balance = libraAccountService.getBalance(libraAccount.getLibraAddressToString());
        balance.setUserId(getAuthedUserInfo().getId());

        return ApiResponse.Success(balance);
    }

    @PostMapping("/mint")
    public ResponseEntity<?> mintLibra(@Valid @RequestBody MintRequest mintRequest) {

        LibraAccount libraAccount = libraAccountService.findAccount(getAuthedUserInfo().getId());

        Long responseAmount = libraAccountService.mint(libraAccount.getLibraAddressToString(), mintRequest.getAmount());

        return ApiResponse.Success(responseAmount);
    }

}
