package com.quickcontact.quickcontact.services;

import com.quickcontact.quickcontact.repositories.CustomerDAO;
import com.quickcontact.quickcontact.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerDAO customerDAO;

    public Optional<CustomerDTO> getCustomerById(Long id) {
        return customerDAO.findById(id);
    }

    public List<CustomerDTO> getAllCustomers() {
        return customerDAO.findAll();
    }

    public void createOrUpdateCustomer(CustomerDTO customer) {
        customerDAO.save(customer);
    }

    public void removeCustomer(Long id) {
        customerDAO.delete(id);
    }
}
