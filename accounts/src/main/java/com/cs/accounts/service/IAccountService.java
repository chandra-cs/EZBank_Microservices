package com.cs.accounts.service;

import com.cs.accounts.dto.AccountDetailsDto;
import com.cs.accounts.dto.CustomerDto;

public interface IAccountService {

    /**
     *
     * @param accountDto AccountDto Object
     */

    public void createAccount(CustomerDto customerDto);

    public AccountDetailsDto fetchAccountDetails(String mobileNumber);

    public Boolean updateAccountDetails(AccountDetailsDto accountDetailsDto);

}
