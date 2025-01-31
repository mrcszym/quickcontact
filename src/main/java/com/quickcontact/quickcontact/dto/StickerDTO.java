package com.quickcontact.quickcontact.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
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
