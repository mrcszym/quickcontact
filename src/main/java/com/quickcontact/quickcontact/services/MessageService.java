package com.quickcontact.quickcontact.services;

import com.quickcontact.quickcontact.dto.MessageDTO;
import com.quickcontact.quickcontact.entities.Message;
import com.quickcontact.quickcontact.entities.Sticker;
import com.quickcontact.quickcontact.repositories.MessageRepository;
import com.quickcontact.quickcontact.repositories.StickerRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageService {

    @Autowired
    @Lazy
    private final MessageRepository messageRepository;

    @Autowired
    @Lazy
    private final StickerRepository stickerRepository;

    public Optional<Message> getMessageById(Long id) {
        return messageRepository.findById(id);
    }

    @Transactional
    public Message addMessage(@Valid MessageDTO messageDTO, Long stickerId) {

        Sticker sticker = stickerRepository.findById(stickerId)
                .orElseThrow(() -> new EntityNotFoundException("Sticker not found with id: " + stickerId));


        Message message = new Message();
        message.setContent(messageDTO.getContent());
        message.setSticker(sticker);

        return messageRepository.save(message);
    }

}
