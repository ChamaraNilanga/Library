package com.library.demo.service.user;

import com.library.demo.dto.requestDTO.UserRequestDTO;
import com.library.demo.dto.responseDTO.UserResponseDTO;
import com.library.demo.entity.User;

public interface UserService {
    UserResponseDTO register(UserRequestDTO userRequestDTO);

    User getUserById(Long userId);
}
