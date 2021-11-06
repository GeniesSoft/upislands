package com.geniessoft.backend.controller;

import com.geniessoft.backend.dto.LocationGetDto;
import com.geniessoft.backend.dto.ProfileImageDto;
import com.geniessoft.backend.dto.UserRegisterDto;
import com.geniessoft.backend.dto.UserUpdateDto;
import com.geniessoft.backend.model.Address;
import com.geniessoft.backend.model.Location;
import com.geniessoft.backend.model.User;
import com.geniessoft.backend.service.UserService;
import com.geniessoft.backend.utility.customvalidator.ImageConstraint;
import com.geniessoft.backend.utility.mapper.CompanyMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class UserController {

    private final UserService userService;
    private final CompanyMapper mapper;

    @PostMapping(value = "/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserRegisterDto userRegisterDto){
        userService.saveUser(userRegisterDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Registration is successful.");
    }

    @PutMapping
    public ResponseEntity<String> updateUser(@Valid @RequestBody UserUpdateDto userUpdateDto){
        userService.updateUser(userUpdateDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Update operation is successful.");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUser(@RequestParam(value = "userId") Integer userId){
        userService.deleteUser(userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Delete operation is successful.");
    }

    @GetMapping
    public ResponseEntity<UserUpdateDto> getUser(@RequestParam(value = "userId") Integer userId){
        User user = userService.findUser(userId);
        UserUpdateDto dto = mapper.userToUserUpdateDto(user);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(dto);
    }

    @GetMapping(value = "/mostBooked")
    public ResponseEntity<UserUpdateDto> getMostBookedUser(){
        User user = userService.findMostBookedUser();
        UserUpdateDto userUpdateDto = mapper.userToUserUpdateDto(user);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userUpdateDto);
    }

    @GetMapping(value = "/bookedUsers")
    public ResponseEntity<List<UserUpdateDto>> getBookedUsersByOrder(){
        List<User> users = userService.findBookedUsersByDescOrder();
        List<UserUpdateDto> userUpdateDtoList = new ArrayList<>();
        for (User user: users) {
            UserUpdateDto userUpdateDto = mapper.userToUserUpdateDto(user);
            userUpdateDtoList.add(userUpdateDto);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userUpdateDtoList);
    }

    @PostMapping(
            path = "{userId}/image/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> uploadUserProfileImage(
            @PathVariable("userId") int userId,
            @RequestParam("file")  @ImageConstraint MultipartFile file){
        userService.uploadUserProfileImage(userId,file);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Image is uploaded.");
    }

    @GetMapping(value = "/{userId}/profileImage")
    public ResponseEntity<ProfileImageDto> getProfileImage(
            @PathVariable("userId") int userId){

        byte[] image = userService.getUserProfileImage(userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ProfileImageDto(image));
    }
}
