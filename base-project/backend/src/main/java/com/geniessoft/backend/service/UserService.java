package com.geniessoft.backend.service;

import com.geniessoft.backend.dto.UserRegisterDto;
import com.geniessoft.backend.dto.UserUpdateDto;
import com.geniessoft.backend.model.User;
import com.geniessoft.backend.utility.customvalidator.Response;

import java.util.Optional;

public interface UserService<T> {

    Optional<User> saveUser(UserRegisterDto user);
    Response<T> updateUser(UserUpdateDto user);
    Response<T> deleteUser(int userId);
    Response<T> findFirstByEmailAddressEquals(String emailAddress);
    Response<T> findUser(int userId);
}
