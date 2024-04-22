package com.hse.service;

import com.hse.model.entity.RefreshToken;
import com.hse.model.entity.UserEntity;
import com.hse.model.request.AuthRequest;
import com.hse.model.request.SignUpRequest;
import com.hse.model.response.JwtResponse;
import com.hse.model.response.MessageResponse;
import com.hse.repo.UserRepository;
import com.hse.security.jwt.JwtUtils;
import com.hse.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    RefreshTokenService refreshTokenService;

    @Autowired
    PasswordEncoder encoder;

    public ResponseEntity<?> authenticateUser(AuthRequest emailRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(emailRequest.getEmail(), emailRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        System.out.println("authenticateUser");
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        System.out.println("до create Refresh");
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

        return ResponseEntity.ok(new JwtResponse(jwt, refreshToken.getToken(), userDetails.getId(),
                userDetails.getEmail(), userDetails.getUsername()));
    }

    public ResponseEntity<?> registerUser(SignUpRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already taken!"));
        }
        UserEntity user = new UserEntity();
        user.setEmail(signUpRequest.getEmail());
        user.setPasswordHash(encoder.encode(signUpRequest.getPassword()));
        user.setRegistrationDate(LocalDateTime.now());
        System.out.println(user.getRegistrationDate());
        userRepository.save(user);
        return authenticateUser(new AuthRequest(user.getEmail(), signUpRequest.getPassword()));
    }
}