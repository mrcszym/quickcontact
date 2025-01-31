package com.quickcontact.quickcontact.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StickerDTO {

    private Long id;
    private String stickerInfo;
    private Long customerId;
    private List<MessageDTO> messages;

    public StickerDTO(Long id, String stickerInfo) {
        this.id = id;
        this.stickerInfo = stickerInfo;
    }

}
