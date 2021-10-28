package com.geniessoft.backend.service;

import com.geniessoft.backend.dto.UserRegisterDto;
import com.geniessoft.backend.dto.UserUpdateDto;
import com.geniessoft.backend.model.User;


public interface UserService {

    User saveUser(UserRegisterDto user);
    User updateUser(UserUpdateDto user);
    void deleteUser(int userId);
    User findFirstByEmailAddressEquals(String emailAddress);
    User findUser(int userId);
    void checkUserEmail(String emailAddress);
}
