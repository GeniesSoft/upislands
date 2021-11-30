package com.geniessoft.backend.controller;

import com.geniessoft.backend.security.FacebookLoginRequest;
import com.geniessoft.backend.model.JwtAuthenticationResponse;
import com.geniessoft.backend.model.LoginRequest;
import com.geniessoft.backend.service.UserService;
import com.geniessoft.backend.security.FacebookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final UserService userService;
    private final FacebookService facebookService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        JwtAuthenticationResponse response = userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/facebook/signin")
    public  ResponseEntity<?> facebookAuth(@Valid @RequestBody FacebookLoginRequest facebookLoginRequest) {
        log.info("facebook login {}", facebookLoginRequest);
        JwtAuthenticationResponse response = facebookService.loginUser(facebookLoginRequest.getAccessToken());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/google/signin")
    public  ResponseEntity<?> googleAuth(@Valid @RequestBody FacebookLoginRequest facebookLoginRequest) {
        log.info("facebook login {}", facebookLoginRequest);
        JwtAuthenticationResponse response = facebookService.loginUser(facebookLoginRequest.getAccessToken());
        return ResponseEntity.ok(response);
    }
}
