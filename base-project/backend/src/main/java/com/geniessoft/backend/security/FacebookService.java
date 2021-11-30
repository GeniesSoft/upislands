package com.geniessoft.backend.security;

import com.geniessoft.backend.exception.InternalServerException;
import com.geniessoft.backend.model.*;
import com.geniessoft.backend.service.ContentService;
import com.geniessoft.backend.service.RoleService;
import com.geniessoft.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class FacebookService {

    private final FacebookClient facebookClient;
    private final UserService userService;
    private final JwtTokenProvider tokenProvider;
    private final RoleService roleService;

    public JwtAuthenticationResponse loginUser(String fbAccessToken) {
        var facebookUser = facebookClient.getUser(fbAccessToken);

        String token = userService.getUserWithEmail(facebookUser.getEmail())
                .or(() -> Optional.ofNullable(userService.save(convertTo(facebookUser))))
                .map(CustomUserDetails::new)
                .map(userDetails -> new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()))
                .map(tokenProvider::generateToken)
                .orElseThrow(() -> new InternalServerException("unable to login facebook user id " + facebookUser.getId()));
        JwtAuthenticationResponse response = new JwtAuthenticationResponse(token);
        response.setPrincipal(facebookUser);
        return response;
    }

    private User convertTo(FacebookUser facebookUser) {

        Content content = new Content();
        content.setContentUrl(facebookUser.getPicture().getData().getUrl());
        Role role = roleService.findRoleByName(Roles.ROLE_USER);

        return User.builder()
                .firstName(facebookUser.getFirstName())
                .lastName(facebookUser.getLastName())
                .emailAddress(facebookUser.getEmail())
                .userProfileImage(content)
                .role(role).build();
    }

    private String generateUsername(String firstName, String lastName) {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        return String.format("%s.%s.%06d", firstName, lastName, number);
    }

    private String generatePassword(int length) {
        String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String specialCharacters = "!@#$";
        String numbers = "1234567890";
        String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
        Random random = new Random();
        char[] password = new char[length];

        password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
        password[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
        password[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
        password[3] = numbers.charAt(random.nextInt(numbers.length()));

        for(int i = 4; i< length ; i++) {
            password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
        }
        return new String(password);
    }
}