package com.quickcontact.quickcontact.services;

import com.quickcontact.quickcontact.dto.StickerDTO;
import com.quickcontact.quickcontact.entities.Sticker;
import com.quickcontact.quickcontact.repositories.StickerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StickerService {

    @Autowired
    private final StickerRepository stickerRepository;

    public Optional<Sticker> getStickerById(Long id) {
        return stickerRepository.findById(id);
    }

    public List<StickerDTO> getAllStickers() {
        List<Sticker> stickers = stickerRepository.findAll();
        return stickers.stream()
                .map(sticker -> new StickerDTO(
                        sticker.getId(),
                        sticker.getStickerInfo(),
                        sticker.getCustomerId()
                ))
                .collect(Collectors.toList());
    }

    @Transactional
    public Sticker addSticker(StickerDTO stickerDTO) {

        Sticker sticker = new Sticker();
        sticker.setStickerInfo(stickerDTO.getStickerInfo());
        sticker.setCustomerId(stickerDTO.getCustomerId());

        return stickerRepository.save(sticker);
    }

}
