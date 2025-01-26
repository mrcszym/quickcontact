package com.quickcontact.quickcontact.services;

import com.quickcontact.quickcontact.dto.CustomerDTO;
import com.quickcontact.quickcontact.entities.User;
import com.quickcontact.quickcontact.exceptions.EmailAlreadyExistsException;
import com.quickcontact.quickcontact.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public CustomerService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public List<CustomerDTO> getAllCustomers() {
        List<User> users = customerRepository.findAll();
        return users.stream()
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
    public User addCustomer(CustomerDTO customerDTO) {

        validateIfEmailAlreadyExists(customerDTO);

        User user = new User();

        user.setEmail(customerDTO.getEmail());
        user.setName(customerDTO.getName());
        user.setPassword(passwordEncoder.encode(customerDTO.getPassword()));
        user.setPhone(customerDTO.getPhone());

        return customerRepository.save(user);
    }

    private void validateIfEmailAlreadyExists(CustomerDTO customerDTO) {
        if (customerRepository.existsByEmail(customerDTO.getEmail())) {
            throw new EmailAlreadyExistsException("Email " + customerDTO.getEmail() + " already exists.");
        }
    }


    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        Optional<User> existingCustomerOpt = customerRepository.findById(id);

        if (existingCustomerOpt.isPresent()) {
            User existingUser = existingCustomerOpt.get();
            existingUser.setName(customerDTO.getName());
            existingUser.setEmail(customerDTO.getEmail());
            existingUser.setPhone(customerDTO.getPhone());

            existingUser = customerRepository.save(existingUser);
            return new CustomerDTO(existingUser.getId(),  existingUser.getEmail(), existingUser.getName(),
                    existingUser.getPassword(), existingUser.getPhone());
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }


//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Optional<User> customer = customerRepository.findByEmail(email);
//
//        UserDetails userDetails =
//                org.springframework.security.core.userdetails.User.builder()
//                        .username(user.getEmail())
//                        .password(customer.getPassword())
//                        .roles(roles.toArray(new String[0]))
//                        .build();
//
//        return userDetails;
//    }

}
