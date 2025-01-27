package com.quickcontact.quickcontact.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "STICKERS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sticker {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "sticker_info")
    private String stickerInfo;

    @Column(name = "customer_id")
    private Long customerId;

}
