package com.geniessoft.backend.oauth2Security.api;

import com.geniessoft.backend.model.User;
import com.geniessoft.backend.oauth2Security.CurrentUser;
import com.geniessoft.backend.oauth2Security.exceptionforsecurity.ResourceNotFoundException;
import com.geniessoft.backend.oauth2Security.UserPrincipal;
import com.geniessoft.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController2 {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('ROLE_USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
}
