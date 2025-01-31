package com.quickcontact.quickcontact.controllers;

import com.quickcontact.quickcontact.dto.StickerDTO;
import com.quickcontact.quickcontact.entities.Sticker;
import com.quickcontact.quickcontact.entities.User;
import com.quickcontact.quickcontact.services.StickerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.quickcontact.quickcontact.controllers.CustomerController.handleErrorMessage;

@RestController
@RequestMapping("/stickers")
public class StickerController {

    @Autowired
    @Lazy
    private StickerService stickerService;

    @GetMapping("/all")
    public List<StickerDTO> getAllStickers() {
        return stickerService.getAllStickers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StickerDTO> getStickerById(@PathVariable Long id) {
        return stickerService.getStickerById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addSticker(@Valid @RequestBody StickerDTO stickerDTO, BindingResult result) {
        if(result.hasErrors()) {
            return handleErrorMessage(result);
        }

        setStickerCustomerIdFromContext(stickerDTO);
        stickerService.addSticker(stickerDTO);

        return ResponseEntity.ok("Sticker created");
    }

    private static void setStickerCustomerIdFromContext(StickerDTO stickerDTO) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            Long userId = ((User) principal).getId();
            stickerDTO.setCustomerId(userId);
        }
    }

}
