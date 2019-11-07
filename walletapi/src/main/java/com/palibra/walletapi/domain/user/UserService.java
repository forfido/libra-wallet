package com.palibra.walletapi.domain.user;

import com.palibra.walletapi.domain.auth.AuthProvider;
import com.palibra.walletapi.domain.auth.SignUpRequest;
import com.palibra.walletapi.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(SignUpRequest signUpRequest) {
        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("Email address already in use.");
        }

        //Create user and wallet
        User user = User.createUser(signUpRequest.getName(), signUpRequest.getEmail(),
                passwordEncoder.encode(signUpRequest.getPassword()), AuthProvider.local, null, null);

        return userRepository.save(user);
    }
}
