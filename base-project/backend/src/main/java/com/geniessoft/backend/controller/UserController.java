package com.geniessoft.backend.controller;

import com.geniessoft.backend.dto.UserRegisterDto;
import com.geniessoft.backend.dto.UserUpdateDto;
import com.geniessoft.backend.dto.denemeDto;
import com.geniessoft.backend.model.User;
import com.geniessoft.backend.service.AnalysisService;
import com.geniessoft.backend.service.UserService;
import com.geniessoft.backend.utility.customvalidator.ImageConstraint;
import com.geniessoft.backend.utility.mapper.CompanyMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(value = "/api/users")
@RequiredArgsConstructor
@Validated
public class UserController {

    private static final String FILE_NAME = "fileName";

    private final UserService userService;
    private final CompanyMapper mapper;
    private final AnalysisService analysisService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public String registerUser(@Valid @RequestBody UserRegisterDto userRegisterDto){
        userService.saveUser(userRegisterDto);
        return "User successfully registered";
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<UserUpdateDto> getAllUsers(){
        return userService.findAllUsers().stream().map(mapper::userToUserUpdateDto).collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public UserUpdateDto getUser(@PathVariable(value = "id") Integer id){
        User user = userService.findUser(id);
        return mapper.userToUserUpdateDto(user);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public String updateUser(@Valid @RequestBody UserUpdateDto userUpdateDto){
        userService.updateUser(userUpdateDto);
        return "User successfully updated";
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{id}")
    public String deleteUser(@PathVariable(value = "id") Integer id){
        userService.deleteUser(id);
        return "User successfully deleted";
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/mostBooked")
    public UserUpdateDto getMostBookedUser(){
        User user = analysisService.findMostBookedUser();
        return mapper.userToUserUpdateDto(user);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/bookedUsers")
    public List<UserUpdateDto> getBookedUsersByOrder(){
        List<User> users = analysisService.findBookedUsersByDescOrder();
        List<UserUpdateDto> userUpdateDtoList = new ArrayList<>();
        for (User user: users) {
            UserUpdateDto userUpdateDto = mapper.userToUserUpdateDto(user);
            userUpdateDtoList.add(userUpdateDto);
        }
        return userUpdateDtoList;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(
            path = "/{userId}/image/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public String uploadUserProfileImage(
            @PathVariable("userId") int userId,
            @RequestParam("file")  @ImageConstraint MultipartFile file){
        userService.uploadUserProfileImage(userId,file);
        return "Image is uploaded.";
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{userId}/profileImage")
    public String getProfileImage(
            @PathVariable("userId") int userId){

        //User user = userService.findUser(userId);
        String url = userService.getUserProfileImageUrl(userId);
        log.info(url);
        return url;
        //byte[] image = userService.getUserProfileImage(userId);
        //return new ProfileImageDto(image);
    }
}
