package com.library.demo.service;

import com.library.demo.dto.requestDTO.UserRequestDTO;
import com.library.demo.entity.User;
import com.library.demo.repository.UserRepository;
import com.library.demo.util.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public CommonResponse register(UserRequestDTO userRequestDTO){
        CommonResponse commonResponse = new CommonResponse();
        User user = new User(
                userRequestDTO.getName(),
                userRequestDTO.getEmail(),
                this.passwordEncoder.encode(userRequestDTO.getPassword()),
                userRequestDTO.getPhoneNumber(),
                userRequestDTO.getCity(),
                userRequestDTO.getCountry(),
                userRequestDTO.getRole()
        );
        Optional<User> email = userRepository.findByEmail(userRequestDTO.getEmail());
        logger.info("this user : {}",email.isPresent());
        if(email.isEmpty()){
            userRepository.save(user);
            commonResponse.setPayload(Collections.singletonList(user));
            commonResponse.setStatus(true);
        }else{
            commonResponse.setPayload(null);
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add("Email Already Exists");
            commonResponse.setErrorMessages(errorMessages);
            commonResponse.setStatus(true);
        }

        return commonResponse;
    }
}
