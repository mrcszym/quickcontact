package com.quickcontact.quickcontact.controllers;

import com.quickcontact.quickcontact.dto.CustomerDTO;
import com.quickcontact.quickcontact.dto.StickerDTO;
import com.quickcontact.quickcontact.entities.User;
import com.quickcontact.quickcontact.services.CustomerService;
import com.quickcontact.quickcontact.services.StickerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
@CrossOrigin(origins = "http://localhost:5173")
public class CustomerController {

    @Autowired
    @Lazy
    private CustomerService customerService;

    @Autowired
    @Lazy
    private StickerService stickerService;

    @GetMapping("/all")
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Optional<User> getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addCustomer(
            @Valid @RequestBody CustomerDTO customerDto, BindingResult result) {
        if(result.hasErrors()) {
            return handleErrorMessage(result);
        }

        customerService.addCustomer(customerDto);

        return ResponseEntity.ok("User with email = " + customerDto.getEmail() + " created.");
    }

    @PutMapping("/{id}")
    public CustomerDTO updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customer) {
        return customerService.updateCustomer(id, customer);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }

    @GetMapping("/my-stickers")
    public List<StickerDTO> getCustomersSticker() {
        if(getCustomerIdFromContext() != null) {
            return stickerService.getStickersByCustomerId(getCustomerIdFromContext());
        }

        return Collections.emptyList();
    }

    private static Long getCustomerIdFromContext() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
             return ((User) principal).getId();
        }
        return null;
    }

    static ResponseEntity<String> handleErrorMessage(BindingResult result) {
        StringBuilder errorMessage = new StringBuilder("Validation failed for fields: ");
        result.getAllErrors().forEach(error -> {
            errorMessage.append(error.getDefaultMessage()).append("; ");
        });
        return ResponseEntity.badRequest().body(errorMessage.toString());
    }

}

