package com.library.demo.service;

import com.library.demo.dto.requestDTO.AuthRequestDTO;
import com.library.demo.entity.User;
import com.library.demo.repository.UserRepository;
import com.library.demo.util.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
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

    private final AuthenticationManager authenticationManager;

    public CommonResponse loginUser (AuthRequestDTO authRequestDTO) {
        CommonResponse commonResponse = new CommonResponse();
        Optional<User> email = userRepository.findByEmail(authRequestDTO.getEmail());
        if(email==null){
            commonResponse.setMessage("Email Not Exists");
        }else{
            var isMatch = this.passwordEncoder.matches(authRequestDTO.getPassword(), email.get().getPassword());
            if(isMatch){
                var token = jwtService.generateJWTToken(authRequestDTO);
                Map<String, String> tokenMap = new HashMap<>();
                tokenMap.put("token", token);
                List<Object> payLoad = new ArrayList<>();
                payLoad.add(tokenMap);
                commonResponse.setPayload(payLoad);
                commonResponse.setMessage("User Logged in Successfully");
                commonResponse.setStatus(true);
            }else{
                commonResponse.setMessage("Invalid Credentials");
            }
        }
        return commonResponse;
    }

}
