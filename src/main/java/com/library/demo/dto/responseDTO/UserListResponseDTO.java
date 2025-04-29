package com.library.demo.dto.responseDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserListResponseDTO {
    private List<UserResponseDTO> users;
}
