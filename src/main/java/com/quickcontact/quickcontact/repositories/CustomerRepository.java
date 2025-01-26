package com.quickcontact.quickcontact.repositories;

import com.quickcontact.quickcontact.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CustomerRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

//    default User findCustomerByEmail(String email){
//        User customer = new User();
//        customer.setEmail(email);
//        return customer;
//    }

    Optional<User> findByEmail(String email);

}
