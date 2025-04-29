package com.library.demo.controller;

import com.library.demo.dto.requestDTO.AuthRequestDTO;
import com.library.demo.service.AuthServcice;
import com.library.demo.util.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login/v1")
public class AuthController {

    private final AuthServcice authServcice;

    @PostMapping("/user-login")
    public ResponseEntity<CommonResponse> loginUser(@RequestBody AuthRequestDTO authRequestDTO) {
            CommonResponse response = authServcice.loginUser(authRequestDTO);
            return ResponseEntity.ok(response);
    }

}
