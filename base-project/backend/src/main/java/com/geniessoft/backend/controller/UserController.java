package com.geniessoft.backend.controller;

import com.geniessoft.backend.dto.UserRegisterDto;
import com.geniessoft.backend.dto.UserUpdateDto;
import com.geniessoft.backend.model.User;
import com.geniessoft.backend.service.UserService;
import com.geniessoft.backend.utility.customvalidator.Response;
import com.geniessoft.backend.utility.customvalidator.SimpleSourceDestinationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.control.MappingControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Slf4j
@RestController
@RequestMapping(value = "/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final SimpleSourceDestinationMapper mapper;

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
