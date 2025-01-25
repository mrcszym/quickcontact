package com.quickcontact.quickcontact.services;

import com.quickcontact.quickcontact.dto.CustomerDTO;
import com.quickcontact.quickcontact.entities.Customer;
import com.quickcontact.quickcontact.exceptions.EmailAlreadyExistsException;
import com.quickcontact.quickcontact.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(customer -> new CustomerDTO(
                        customer.getId(),
                        customer.getEmail(),
                        customer.getName(),
                        customer.getPassword(),
                        customer.getPhone()
                ))
                .collect(Collectors.toList());
    }

    @Transactional
    public Customer addCustomer(CustomerDTO customerDTO) {

        validateIfEmailAlreadyExists(customerDTO);

        Customer customer = new Customer();

        customer.setEmail(customerDTO.getEmail());
        customer.setName(customerDTO.getName());
        customer.setPassword(customerDTO.getPassword());
        customer.setPhone(customerDTO.getPhone());

        return customerRepository.save(customer);
    }

    private void validateIfEmailAlreadyExists(CustomerDTO customerDTO) {
        if (customerRepository.existsByEmail(customerDTO.getEmail())) {
            throw new EmailAlreadyExistsException("Email " + customerDTO.getEmail() + " already exists.");
        }
    }


    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        Optional<Customer> existingCustomerOpt = customerRepository.findById(id);

        if (existingCustomerOpt.isPresent()) {
            Customer existingCustomer = existingCustomerOpt.get();
            existingCustomer.setName(customerDTO.getName());
            existingCustomer.setEmail(customerDTO.getEmail());
            existingCustomer.setPhone(customerDTO.getPhone());

            existingCustomer = customerRepository.save(existingCustomer);
            return new CustomerDTO(existingCustomer.getId(),  existingCustomer.getEmail(), existingCustomer.getName(),
                    existingCustomer.getPassword(), existingCustomer.getPhone());
        } else {
            throw new RuntimeException("Customer not found");
        }
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

}
