package com.library.demo.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CommonResponse {
    private boolean status;
    private String message;
    private Map<String, String> payload;
}
