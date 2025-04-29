package com.library.demo.service;

import com.library.demo.dto.requestDTO.AuthRequestDTO;
import com.library.demo.entity.User;
import com.library.demo.exception.DataValidationException;
import com.library.demo.repository.UserRepository;
import com.library.demo.util.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServcice {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    public CommonResponse loginUser(AuthRequestDTO authRequestDTO) {
        CommonResponse commonResponse = new CommonResponse();

        User user = userRepository.findByEmail(authRequestDTO.getEmail())
                .orElseThrow(() -> new DataValidationException("Email not registered",authRequestDTO.getEmail()));

        if (!passwordEncoder.matches(authRequestDTO.getPassword(), user.getPassword())) {
            throw new DataValidationException("Invalid Credentials for User", user.getEmail());
        }

        String token = jwtService.generateJWTToken(authRequestDTO);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);

        commonResponse.setPayload(tokenMap);
        commonResponse.setMessage("User Logged in Successfully");
        commonResponse.setStatus(true);
        return commonResponse;
    }
}
