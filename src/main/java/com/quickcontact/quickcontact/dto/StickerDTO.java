package com.quickcontact.quickcontact.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StickerDTO {

    private Long id;

    @NotNull(message = "Sticker content may not be null")
    private String stickerInfo;

    @Column(name = "customer_id")
    private Long customerId;
}
