package com.quickcontact.quickcontact.repositories;

import com.quickcontact.quickcontact.dto.CustomerDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomerDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<CustomerDTO> findById(Long customerId) {
        CustomerDTO customer = entityManager.find(CustomerDTO.class, customerId);
        return Optional.ofNullable(customer);
    }

    public List<CustomerDTO> findAll() {
        return entityManager.createQuery("SELECT c FROM customers c", CustomerDTO.class)
                .getResultList();
    }

    @Transactional
    public void save(CustomerDTO customer) {
        if (customer.getCustomerId() == null) {
            entityManager.persist(customer);
        } else {
            entityManager.merge(customer);
        }
    }

    @Transactional
    public void update(CustomerDTO customer) {
        entityManager.merge(customer);
    }

    @Transactional
    public void delete(Long customerId) {
        CustomerDTO customer = entityManager.find(CustomerDTO.class, customerId);
        if (customer != null) {
            entityManager.remove(customer);
        }
    }

}
