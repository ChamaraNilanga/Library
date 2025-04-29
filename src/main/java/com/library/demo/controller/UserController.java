package com.library.demo.controller;

import com.library.demo.dto.requestDTO.UserRequestDTO;
import com.library.demo.dto.responseDTO.UserResponseDTO;
import com.library.demo.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user/v1")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser (
            @RequestBody UserRequestDTO userRequestDTO
    ){
        log.info("CALLED USER CREATION END POINT");
        return new ResponseEntity<>(userService.register(userRequestDTO), HttpStatus.OK);
    }
}
