package com.library.demo.exception;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DataValidationException extends RuntimeException{

    private String message;
    private String property;

    public String getMessage(){
        return String.format("%s : %s " , message , property);
    }

}
