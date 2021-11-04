package com.geniessoft.backend.service;

import com.geniessoft.backend.dto.UserRegisterDto;
import com.geniessoft.backend.dto.UserUpdateDto;
import com.geniessoft.backend.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;


public interface UserService {

    User saveUser(UserRegisterDto user);

    User updateUser(UserUpdateDto user);

    User findFirstByEmailAddressEquals(String emailAddress);

    User findMostBookedUser();

    List<User> findBookedUsersByAscOrder();

    User findUser(int userId);

    void uploadUserProfileImage(int userId, MultipartFile file);

    void deleteUser(int userId);

    void checkUserEmail(String emailAddress);

    void deleteUserProfileImage(User user);

    byte[] getUserProfileImage(int userId);
}
