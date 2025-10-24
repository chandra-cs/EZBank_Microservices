package com.cs.accounts.service.impl;

import com.cs.accounts.constants.AccountConstants;
import com.cs.accounts.dto.AccountDetailsDto;
import com.cs.accounts.dto.AccountDto;
import com.cs.accounts.dto.CustomerDto;
import com.cs.accounts.entity.Account;
import com.cs.accounts.entity.Customer;
import com.cs.accounts.exception.CustomerAlreadyExistsException;
import com.cs.accounts.exception.CustomerNotFoundException;
import com.cs.accounts.exception.InvalidAccountNumberException;
import com.cs.accounts.mapper.AccountMapper;
import com.cs.accounts.mapper.CustomerMapper;
import com.cs.accounts.repository.IAccountRepository;
import com.cs.accounts.repository.ICustomerRepository;
import com.cs.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private final IAccountRepository accountRepository;
    private  final ICustomerRepository customerRepository;

    /*
    public AccountServiceImpl(IAccountRepository accountRepository, ICustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    } */

    @Override
    public void createAccount(CustomerDto customerDto) {

        //mapping the dto to entity
        Customer customer = CustomerMapper.mapToCustomer(customerDto,new Customer());

        //validating whether customer alreadyExistsOrNot
        Optional<Customer> customerByMobileNumberAndEmail = customerRepository.findByMobileNumberAndEmail(customerDto.getMobileNumber(), customerDto.getEmail());
        if (customerByMobileNumberAndEmail.isPresent()) {
            throw new CustomerAlreadyExistsException("Account already exists with Mobile Number or Email ");
        }


        //persist the customer obj
        Customer savedCustomer = customerRepository.save(customer); //account created

        //create account using helper method
        Account account = createNewAccount(savedCustomer);

        //persist the account object
        accountRepository.save(account);

    }

    /**
     *
     * @param mobileNumber
     * @return AccountDetailsDto
     */


    @Override
    public AccountDetailsDto fetchAccountDetails(String mobileNumber){

        //fetch the Customer by mobile num
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(()->new CustomerNotFoundException("Customer not found with mobile number "+mobileNumber));

        //convert the customer into CustomerDto
        CustomerDto  customerDto = CustomerMapper.mapToCustomerDto(customer,new CustomerDto());


        //get the customerId & get the associated account
        Account account = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(()->new CustomerNotFoundException("Customer not found with associated Customer Id  "));


        //convert the account into AccountDTO
        AccountDto accountDto = AccountMapper.mapToAccountsDto(account,new AccountDto());

        //now using both CustomerDto and AccountDto construct AccountDetailsDto
        return new AccountDetailsDto(accountDto,customerDto);
    }



    @Override
    public Boolean updateAccountDetails(AccountDetailsDto accountDetailsDto) {
        Long accountNumber = accountDetailsDto.getAccountDto().getAccountNumber();
        
        //find Account by account number
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new InvalidAccountNumberException("Invalid Account Number "));


        return null;
    }



    /**
     * create Account (Helper Method To Create Account)
     *
     * @param Customer
     * @return Account
     */

    private Account createNewAccount(Customer customer) {
        Account newAccount = new Account();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountConstants.SAVINGS);
        newAccount.setBranchAddress(AccountConstants.ADDRESS);
        newAccount.setCreatedBy("anonymous");
        return newAccount;
    }




}
