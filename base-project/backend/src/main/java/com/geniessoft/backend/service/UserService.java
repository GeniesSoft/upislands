package com.geniessoft.backend.service;

import com.geniessoft.backend.dto.UserRegisterDto;
import com.geniessoft.backend.dto.UserUpdateDto;
import com.geniessoft.backend.model.JwtAuthenticationResponse;
import com.geniessoft.backend.model.User;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Optional;

public interface UserService {

    User saveUser(UserRegisterDto user);

    User updateUser(UserUpdateDto user);

    User findFirstByEmailAddressEquals(String emailAddress);

    User findByEmailAddress(String emailAddress);

    JwtAuthenticationResponse loginUser(String username, String password);

    User save(User user);

    List<User> findAllUsers();

    User findUser(long userId);

    Optional<User> getUserWithEmail(String email);

    void uploadUserProfileImage(int userId, MultipartFile file);

    void deleteUser(int userId);

    void checkUserEmail(String emailAddress);

    void deleteUserProfileImage(User user);

    byte[] getUserProfileImage(int userId);

    String getUserProfileImageUrl(long userId);
}
