package com.library.demo.controller;

import com.library.demo.dto.requestDTO.AuthRequestDTO;
import com.library.demo.service.AuthServcice;
import com.library.demo.util.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login/v1")
public class AuthController {

    private final AuthServcice authServcice;

    @PostMapping
    public CommonResponse loginUser (@RequestBody AuthRequestDTO authRequestDTO){
        return authServcice.loginUser(authRequestDTO);
    }

}
