package com.palibra.walletapi.controller;

import com.palibra.walletapi.controller.common.ApiResponse;
import com.palibra.walletapi.controller.common.TokenBaseController;
import com.palibra.walletapi.domain.user.User;
import com.palibra.walletapi.domain.user.UserDto;
import com.palibra.walletapi.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController extends TokenBaseController {

    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser() {

        User user = userService.getUserInfo(getAuthedUserInfo().getId());

        return ApiResponse.Success(user.getEmail());
    }

    @GetMapping("/search/email/{keyword}")
    public ResponseEntity<?> searchEmailKeyword(@PathVariable String keyword) {

        List<UserDto> users = userService.searchEmailsByKeyword(keyword);

        return ApiResponse.Success(users);
    }



}
