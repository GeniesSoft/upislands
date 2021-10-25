package com.geniessoft.backend.controller;

import com.geniessoft.backend.dto.UserRegisterDto;
import com.geniessoft.backend.dto.UserUpdateDto;
import com.geniessoft.backend.model.User;
import com.geniessoft.backend.service.UserService;
import com.geniessoft.backend.utility.customvalidator.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
public class UserOperations {

    private final UserService userService;

    @PostMapping(value = "/register")
    public ResponseEntity<String> userRegister(@Valid @RequestBody UserRegisterDto userRegisterDto){
        Optional<User> response = userService.saveUser(userRegisterDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("success");
    }

    @PutMapping
    public ResponseEntity<String> userUpdate(@Valid @RequestBody UserUpdateDto userUpdateDto){
        Response<User> response = userService.updateUser(userUpdateDto);
        return ResponseEntity
                .status(response.getStatus())
                .body(response.getMessage());
    }

    @DeleteMapping
    public ResponseEntity<String> userDelete(@RequestParam(value = "userId") Long userId){
        Response<User> response = userService.deleteUser(userId);
        return ResponseEntity
                .status(response.getStatus())
                .body(response.getMessage());
    }

    @GetMapping
    public ResponseEntity<?> userGet(@RequestParam(value = "userId") Long userId){
        Response<User> response = userService.findUser(userId);
        if(response.getStatus() != HttpStatus.OK){
            return ResponseEntity
                    .status(response.getStatus())
                    .body(response.getMessage());
        }
        return ResponseEntity
                .status(response.getStatus())
                .body(response.getOptionalT().get());
    }

}
