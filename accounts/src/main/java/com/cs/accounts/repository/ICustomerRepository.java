package com.cs.accounts.repository;

import com.cs.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {



    public Optional<Customer> findByMobileNumber(String mobileNumber);
    public Optional<Customer> findByEmail(String email);

}
