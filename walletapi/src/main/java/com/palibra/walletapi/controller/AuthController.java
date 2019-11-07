package com.palibra.walletapi.controller;

import com.palibra.walletapi.controller.common.ApiResponse;
import com.palibra.walletapi.domain.auth.AuthProvider;
import com.palibra.walletapi.domain.auth.AuthResponse;
import com.palibra.walletapi.domain.auth.LoginRequest;
import com.palibra.walletapi.domain.auth.SignUpRequest;
import com.palibra.walletapi.domain.libraaccount.Account;
import com.palibra.walletapi.domain.libraaccount.LibraAccountRepository;
import com.palibra.walletapi.domain.libraaccount.LibraAccount;
import com.palibra.walletapi.domain.libraaccount.LibraAccountService;
import com.palibra.walletapi.domain.user.User;
import com.palibra.walletapi.domain.user.UserRepository;
import com.palibra.walletapi.exception.BadRequestException;
import com.palibra.walletapi.security.TokenProvider;
import dev.jlibra.util.JLibraUtil;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LibraAccountRepository libraAccountRepository;

    @Autowired
    private LibraAccountService libraAccountService;

    @Autowired
    private JLibraUtil jLibraUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication);
        return ApiResponse.Success(new AuthResponse(token));
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("Email address already in use.");
        }

        //Create user and wallet
        User user = User.createUser(signUpRequest.getName(), signUpRequest.getEmail(),
                passwordEncoder.encode(signUpRequest.getPassword()), AuthProvider.local, null, null);

        userRepository.save(user);

        //Create Libra Address by JLibra
        Account libraAccount = libraAccountService.createAccount();

        //Save on Palibra wallet
        LibraAccount account = LibraAccount.createAccount("LibraWallet", libraAccount.getAddress(), libraAccount.getPrivateKey(), libraAccount.getPublicKey(), user);
        libraAccountRepository.save(account);

        //1000 Test LBR provided
        jLibraUtil.mint(Hex.toHexString(account.getLibraAddress()), 1000L);

//        URI location = ServletUriComponentsBuilder
//                .fromCurrentContextPath().path("/user/me")
//                .buildAndExpand(result.getId()).toUri();

        //return ResponseEntity.ok().body(account.getLibraAddress());
        return ApiResponse.Success(libraAccount);
    }
}
