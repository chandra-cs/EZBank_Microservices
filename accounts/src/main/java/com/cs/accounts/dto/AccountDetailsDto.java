package com.cs.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDetailsDto {

    private  AccountDto accountDto;

    private  CustomerDto customerDto;


}
