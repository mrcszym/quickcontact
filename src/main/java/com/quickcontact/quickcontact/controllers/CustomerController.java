package com.quickcontact.quickcontact.controllers;

import com.quickcontact.quickcontact.dto.CustomerDTO;
import com.quickcontact.quickcontact.entities.Customer;
import com.quickcontact.quickcontact.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/all")
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Optional<Customer> getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addCustomer(@Valid @RequestBody CustomerDTO customerDto, BindingResult result) {
        if(result.hasErrors()) {
            return handleErrorMessage(result);
        }

        customerService.addCustomer(customerDto);

        return ResponseEntity.ok("Customer with email = " + customerDto.getEmail() + " created.");
    }

    private static ResponseEntity<String> handleErrorMessage(BindingResult result) {
        StringBuilder errorMessage = new StringBuilder("Validation failed for fields: ");
        result.getAllErrors().forEach(error -> {
            errorMessage.append(error.getDefaultMessage()).append("; ");
        });
        return ResponseEntity.badRequest().body(errorMessage.toString());
    }

    @PutMapping("/{id}")
    public CustomerDTO updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customer) {
        return customerService.updateCustomer(id, customer);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }
}

