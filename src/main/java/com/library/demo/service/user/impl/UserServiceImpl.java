package com.library.demo.service.user.impl;

import com.library.demo.dto.requestDTO.UserRequestDTO;
import com.library.demo.dto.responseDTO.UserResponseDTO;
import com.library.demo.entity.User;
import com.library.demo.exception.DataValidationException;
import com.library.demo.exception.ResourceNotFoundException;
import com.library.demo.repository.UserRepository;
import com.library.demo.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDTO register(UserRequestDTO userRequestDTO){
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        User user = new User();
        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(this.passwordEncoder.encode(userRequestDTO.getPassword()));
        user.setRole(userRequestDTO.getRole());
        userRepository.findByEmail(userRequestDTO.getEmail())
                .ifPresent(record -> {
                    throw new DataValidationException(
                            "User already registered with email.", userRequestDTO.getEmail()
                    );
                });

        user = userRepository.save(user);
        BeanUtils.copyProperties(user,userResponseDTO);

        return userResponseDTO;
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
    }
}
