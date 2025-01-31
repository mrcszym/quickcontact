package com.quickcontact.quickcontact.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {

    private Long id;

    @NotNull(message = "Message content may not be null")
    private String content;

}
