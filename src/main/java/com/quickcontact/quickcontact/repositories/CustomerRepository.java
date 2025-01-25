package com.quickcontact.quickcontact.repositories;

import com.quickcontact.quickcontact.dto.CustomerDTO;
import com.quickcontact.quickcontact.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsByEmail(String email);

}
