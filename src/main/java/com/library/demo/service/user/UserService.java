package com.library.demo.service.user;

import com.library.demo.dto.requestDTO.UserRequestDTO;
import com.library.demo.dto.responseDTO.UserResponseDTO;

public interface UserService {
    UserResponseDTO register(UserRequestDTO userRequestDTO);
}
