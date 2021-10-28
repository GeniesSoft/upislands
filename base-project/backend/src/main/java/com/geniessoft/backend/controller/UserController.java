package com.geniessoft.backend.controller;

import com.geniessoft.backend.dto.UserRegisterDto;
import com.geniessoft.backend.dto.UserUpdateDto;
import com.geniessoft.backend.model.User;
import com.geniessoft.backend.service.UserService;
import com.geniessoft.backend.utility.mapper.CompanyMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Slf4j
@RestController
@RequestMapping(value = "/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final CompanyMapper mapper;

    @PostMapping(value = "/register")
    public ResponseEntity<String> userRegister(@Valid @RequestBody UserRegisterDto userRegisterDto){
        userService.saveUser(userRegisterDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Registration is successful.");
    }

    @PutMapping
    public ResponseEntity<String> userUpdate(@Valid @RequestBody UserUpdateDto userUpdateDto){
        userService.updateUser(userUpdateDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Update operation is successful.");
    }

    @DeleteMapping
    public ResponseEntity<String> userDelete(@RequestParam(value = "userId") Integer userId){
        userService.deleteUser(userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Delete operation is successful.");
    }

    @GetMapping
    public ResponseEntity<UserUpdateDto> userGet(@RequestParam(value = "userId") Integer userId){
        User user = userService.findUser(userId);
        UserUpdateDto dto = mapper.userToUserUpdateDto(user);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(dto);
    }

}
