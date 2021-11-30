package com.geniessoft.backend.controller;

import com.geniessoft.backend.dto.LocationGetDto;
import com.geniessoft.backend.dto.ProfileImageDto;
import com.geniessoft.backend.dto.UserRegisterDto;
import com.geniessoft.backend.dto.UserUpdateDto;
import com.geniessoft.backend.model.Address;
import com.geniessoft.backend.model.Location;
import com.geniessoft.backend.model.User;
import com.geniessoft.backend.service.AnalysisService;
import com.geniessoft.backend.service.UserService;
import com.geniessoft.backend.service.impl.FileStoreService;
import com.geniessoft.backend.utility.bucket.BucketName;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    private final FileStoreService fileStoreService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public String registerUser(@Valid @RequestBody UserRegisterDto userRegisterDto){
        userService.saveUser(userRegisterDto);
        return "User successfully registered";
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
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
        UserUpdateDto userUpdateDto = mapper.userToUserUpdateDto(user);
        return userUpdateDto;
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
    public String uploadUserProfileImage(
            @PathVariable("userId") int userId,
            @RequestParam("file")  @ImageConstraint MultipartFile file){
        userService.uploadUserProfileImage(userId,file);
        return "Image is uploaded.";
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{userId}/profileImage")
    public ProfileImageDto getProfileImage(
            @PathVariable("userId") int userId){

        User user = userService.findUser(userId);
        String url = fileStoreService.getPreSignedDownloadUrl(
                BucketName.BUCKET_NAME.getBucketName(),
                user.getUserProfileImage().getContentName());

        log.info(url);

        byte[] image = userService.getUserProfileImage(userId);
        return new ProfileImageDto(image);
    }
}
