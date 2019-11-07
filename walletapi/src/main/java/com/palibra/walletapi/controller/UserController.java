package com.palibra.walletapi.controller;

import com.palibra.walletapi.controller.common.ApiResponse;
import com.palibra.walletapi.domain.user.User;
import com.palibra.walletapi.domain.user.UserRepository;
import com.palibra.walletapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@RequestParam Long id) {

        User user = userRepository.findById(id)
                            .orElseThrow( () -> new ResourceNotFoundException("User", "id", id) );

        return ApiResponse.Success(user.getEmail());
    }

//    @GetMapping("/balance")
//    public
}
