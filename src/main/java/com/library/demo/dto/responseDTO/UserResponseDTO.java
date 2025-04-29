package com.library.demo.dto.responseDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponseDTO {
    private String name;
    private String email;
    private String role;
}
