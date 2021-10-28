package com.geniessoft.backend.service.impl;

import com.geniessoft.backend.dto.UserRegisterDto;
import com.geniessoft.backend.dto.UserUpdateDto;
import com.geniessoft.backend.model.Role;
import com.geniessoft.backend.model.Roles;
import com.geniessoft.backend.model.User;
import com.geniessoft.backend.repository.UserRepository;
import com.geniessoft.backend.service.RoleService;
import com.geniessoft.backend.service.UserService;
import com.geniessoft.backend.utility.customvalidator.CompanyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final CompanyMapper mapper;
    private final RoleService roleService;
    private final UserRepository userRepository;

    @Override
    public User saveUser(UserRegisterDto userRegisterDto) {

        User user = mapper.userRegDtoToUser(userRegisterDto);
        checkUserEmail(user.getEmailAddress());

        Role role = roleService.findRoleByName(Roles.ROLE_USER);
        user.setRole(role);
        userRepository.save(user);

        return user;
    }

    @Override
    public User updateUser(UserUpdateDto userUpdateDto) {

        User user = findUser(userUpdateDto.getUserId());
        if(!user.getEmailAddress().equals(userUpdateDto.getEmailAddress())){
            checkUserEmail(userUpdateDto.getEmailAddress());
        }
        mapper.updateUser(user,userUpdateDto);
        userRepository.save(user);

        return user;
    }

    @Override
    public void deleteUser(int userId) {
        User user = findUser(userId);
        user.setDeleted(true);
    }

    @Override
    public User findUser(int userId) {

        Optional<User> user = userRepository.findByUserId(userId);
        return customUserFind(user);
    }

    @Override
    public User findFirstByEmailAddressEquals(String emailAddress) {
        Optional<User> user = userRepository.findFirstByEmailAddressEquals(emailAddress);
        return customUserFind(user);
    }

    private User customUserFind(Optional<User> user){
        if(user.isEmpty()){
            throw new EntityNotFoundException("User not found.");
        }
        else if(user.get().isDeleted()){
            throw new EntityNotFoundException("No further information is to be supplied");
        }
        else {
            return user.get();
        }
    }

    @Override
    public void checkUserEmail(String emailAddress) {
        Optional<User> user = userRepository.findFirstByEmailAddressEquals(emailAddress);
        if(!user.isEmpty()){
            throw new EntityExistsException("This email address is used.");
        }
    }
}
