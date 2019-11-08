package com.palibra.walletapi.controller;

import com.palibra.walletapi.controller.common.ApiResponse;
import com.palibra.walletapi.controller.common.TokenBaseController;
import com.palibra.walletapi.domain.libraaccount.LibraAccount;
import com.palibra.walletapi.domain.libraaccount.LibraAccountService;
import com.palibra.walletapi.domain.user.User;
import com.palibra.walletapi.domain.user.UserRepository;
import com.palibra.walletapi.domain.user.UserService;
import com.palibra.walletapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController extends TokenBaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private LibraAccountService libraAccountService;

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser() {

        User user = userService.getUserInfo(getAuthedUserInfo().getId());

        return ApiResponse.Success(user.getEmail());
    }

    @GetMapping("/balance")
    public ResponseEntity<?> getBalance() {

        LibraAccount libraAccount = libraAccountService.findAccount(getAuthedUserInfo().getId());

        Long balance = libraAccountService.getBalance(libraAccount.getLibraAddressToString());
        return ApiResponse.Success(balance);
    }
}
