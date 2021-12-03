package com.geniessoft.backend.oauth2Security.api;

import com.geniessoft.backend.model.User;
import com.geniessoft.backend.oauth2Security.CurrentUser;
import com.geniessoft.backend.oauth2Security.exceptionforsecurity.ResourceNotFoundException;
import com.geniessoft.backend.oauth2Security.UserPrincipal;
import com.geniessoft.backend.oauth2Security.payload.CurrentUserInfo;
import com.geniessoft.backend.repository.UserRepository;
import com.geniessoft.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@Slf4j
@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
public class GetCurrentUser {

    private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping("/user/me")
    public CurrentUserInfo getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {

        User user = userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));

        String url = userService.getUserProfileImageUrl(user.getUserId());

        CurrentUserInfo currentUserInfo = new CurrentUserInfo();
        currentUserInfo.setEmail(user.getEmailAddress());
        currentUserInfo.setFirstName(user.getFirstName());
        currentUserInfo.setLastName(user.getLastName() == null ? "":user.getLastName());
        currentUserInfo.setId(user.getUserId());
        currentUserInfo.setAvatar(url);

        ArrayList<String> roles = new ArrayList<>();
        roles.add(user.getRole().getRoleName().name());
        String[] rolesArry = new String[roles.size()];
        currentUserInfo.setRoles(roles.toArray(rolesArry));

        return currentUserInfo;
    }
}
