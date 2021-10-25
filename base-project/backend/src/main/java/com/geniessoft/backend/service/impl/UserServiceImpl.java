package com.geniessoft.backend.service.impl;

import com.geniessoft.backend.dto.UserRegisterDto;
import com.geniessoft.backend.dto.UserUpdateDto;
import com.geniessoft.backend.model.Role;
import com.geniessoft.backend.model.Roles;
import com.geniessoft.backend.model.User;
import com.geniessoft.backend.repository.UserRepository;
import com.geniessoft.backend.service.RoleService;
import com.geniessoft.backend.service.UserService;
import com.geniessoft.backend.utility.customvalidator.Response;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final RoleService roleService;
    private final UserRepository userRepository;

    @Override
    public Optional<User> saveUser(UserRegisterDto userRegisterDto) {

        User user = modelMapper.map(userRegisterDto,User.class);

        Optional<User> userTemp =  findFirstByEmailAddressEquals
                (user.getEmailAddress()).getOptionalT();

        if(!userTemp.isEmpty()){
            if(userTemp.get().isDeleted()){
                throw new EntityNotFoundException("123");
            }
            else {
                throw new EntityNotFoundException("1234");
            }
        }
        else {
            Role role = roleService.findRoleByName(Roles.ROLE_USER);
            user.setRole(role);
            userRepository.save(user);
            return userTemp;
        }
    }

    @Override
    public Response<User> updateUser(UserUpdateDto userUpdateDto) {

        Optional<User> user = findUser(userUpdateDto.getUserId()).getOptionalT();

        if(user.isEmpty()){
            return new Response<>(
                    "User is not found.",
                    HttpStatus.NOT_FOUND);
        }
        else if(user.get().isDeleted()){
            return new Response<>(
                    "User is disabled.",
                    HttpStatus.FORBIDDEN);
        }
        else{
            User userTemp = modelMapper.map(userUpdateDto, User.class);
            userTemp.setRole(user.get().getRole());
            userTemp.setPassword(user.get().getPassword());
            userTemp.setEmailAddress(user.get().getEmailAddress());
            userRepository.save(userTemp);
            return new Response<>(
                    "Update operation is successful",
                            HttpStatus.OK);
        }
    }

    @Override
    public Response<User> deleteUser(long userId) {
        Response<User> response = findUser(userId);
        if(response.getOptionalT().isEmpty() ||
                response.getOptionalT().get().isDeleted()){
            return response;
        }
        else {
            User user = response.getOptionalT().get();
            user.setDeleted(true);
            userRepository.save(user);
            return new Response<>(
                    "User is deleted",
                            HttpStatus.OK);
        }
    }

    @Override
    public Response<User> findUser(long userId) {

        Optional<User> user = userRepository.findById(userId);
        return customUserFind(user);
    }

    @Override
    public Response<User> findFirstByEmailAddressEquals(String emailAddress) {
        Optional<User> user = userRepository.findFirstByEmailAddressEquals(emailAddress);
        return customUserFind(user);
    }

    private Response<User> customUserFind(Optional<User> user){
        if(user.isEmpty()){
            return new Response<>(
                    user,
                    "User not found.",
                    HttpStatus.NOT_FOUND);
        }
        else if(user.get().isDeleted()){
            return new Response<>(
                    user,
                    "No further information is to be supplied",
                    HttpStatus.FORBIDDEN);
        }
        else {
            return new Response<>(
                    user,
                    "User is found.",
                    HttpStatus.OK);
        }
    }
}
