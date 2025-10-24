package com.cs.accounts.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class InvalidAccountNumberException extends RuntimeException{

    private String errorMessage;

}
