package com.quickcontact.quickcontact.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginErrorResponse {

    HttpStatus httpStatus;
    String message;

}
