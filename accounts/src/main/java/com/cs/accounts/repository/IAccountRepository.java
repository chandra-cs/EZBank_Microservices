package com.cs.accounts.repository;

import com.cs.accounts.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAccountRepository   extends JpaRepository<Account, Long> {

    public Optional<Account> findByCustomerId(Long customerId);

    public Optional<Account> findByAccountNumber(Long accountNumber);

}
