package com.quickcontact.quickcontact.controllers;

import com.quickcontact.quickcontact.dto.MessageDTO;
import com.quickcontact.quickcontact.dto.StickerDTO;
import com.quickcontact.quickcontact.entities.Message;
import com.quickcontact.quickcontact.services.MessageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static com.quickcontact.quickcontact.controllers.CustomerController.handleErrorMessage;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    @Lazy
    private MessageService messageService;

    @PostMapping("/add/{stickerId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addMessage(@Valid @RequestBody MessageDTO messageDTO, @PathVariable Long stickerId, BindingResult result) {
        if(result.hasErrors()) {
            return handleErrorMessage(result);
        }

        messageService.addMessage(messageDTO, stickerId);

        return ResponseEntity.ok("Message created");
    }

}
