package com.quickcontact.quickcontact.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    private Long id;

    @NotNull(message = "Email may not be null")
    @Email(message = "Email is not valid")
    private String email;

    @NotNull(message = "Name may not be null")
    private String name;

    @NotNull(message = "Password may not be null")
    private String password;

    private String phone;

}
