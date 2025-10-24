package com.cs.accounts.controller;

import com.cs.accounts.constants.AccountConstants;
import com.cs.accounts.dto.*;
import com.cs.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account-api")
@AllArgsConstructor
public class AccountsController {

    private final IAccountService accountService;


    @PostMapping("/create-account")
    public ResponseEntity<SuccessResponseDto> createAccount(@RequestBody CustomerDto customerDto) {
        accountService.createAccount(customerDto);
        return new ResponseEntity<>(new SuccessResponseDto(AccountConstants.STATUS_200,AccountConstants.MESSAGE_201), HttpStatus.CREATED);
    }

    @GetMapping("/fetch-account")
    public ResponseEntity<AccountDetailsDto> fetchAccountsDetail(@RequestParam String mobileNumber) {
        AccountDetailsDto accountDetailsDto = accountService.fetchAccountDetails(mobileNumber);
        return new ResponseEntity<>(accountDetailsDto, HttpStatus.OK);
    }

    @PutMapping("/update-account")
    public ResponseEntity<SuccessResponseDto> updateAccount(@RequestBody UpdateAccountDetailsDto updateAccountDetailsDto) {
        return null;
    }


}
