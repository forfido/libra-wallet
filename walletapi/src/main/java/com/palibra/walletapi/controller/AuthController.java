package com.palibra.walletapi.controller;

import com.palibra.walletapi.controller.common.ApiResponse;
import com.palibra.walletapi.domain.account.Account;
import com.palibra.walletapi.domain.account.LibraAccount;
import com.palibra.walletapi.domain.account.AccountRepository;
import com.palibra.walletapi.domain.auth.AuthProvider;
import com.palibra.walletapi.domain.auth.AuthResponse;
import com.palibra.walletapi.domain.auth.LoginRequest;
import com.palibra.walletapi.domain.auth.SignUpRequest;
import com.palibra.walletapi.domain.user.User;
import com.palibra.walletapi.domain.user.UserRepository;
import com.palibra.walletapi.domain.wallet.LibraWalletService;
import com.palibra.walletapi.domain.wallet.Wallet;
import com.palibra.walletapi.domain.wallet.WalletRepository;
import com.palibra.walletapi.exception.BadRequestException;
import com.palibra.walletapi.security.TokenProvider;
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
    private AccountRepository accountRepository;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private LibraWalletService libraWalletService;

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
        User user = User.builder()
                .setEmail(signUpRequest.getEmail())
                .setName(signUpRequest.getName())
                .setPassword(passwordEncoder.encode(signUpRequest.getPassword()))
                .setProvider(AuthProvider.local)
                .build();
        //TODO : 1대1 주테이블에 값 매핑안되어 있음
        //user.setWallet(wallet);


        User result = userRepository.save(user);
        Wallet wallet = new Wallet(result);

        walletRepository.save(wallet);

        //Create Libra Address by JLibra
        Account libraAccount = libraWalletService.createAccount();

        //Test Promotion 1000 LBR
        LibraAccount account = LibraAccount.createAccount("LibraWallet", "LBR", libraAccount.getAddress(), libraAccount.getPrivateKey(), libraAccount.getPublicKey(), wallet);
        accountRepository.save(account);

//        URI location = ServletUriComponentsBuilder
//                .fromCurrentContextPath().path("/user/me")
//                .buildAndExpand(result.getId()).toUri();

        //return ResponseEntity.ok().body(account.getLibraAddress());
        return ApiResponse.Success(account.getLibraAddress());
    }
}
