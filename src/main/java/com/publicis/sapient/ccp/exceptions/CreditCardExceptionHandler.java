package com.publicis.sapient.ccp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CreditCardExceptionHandler {

    @ExceptionHandler(CreditCardExistException.class)
public ResponseEntity<String> handleCreditCardExistException(CreditCardExistException creditCardExistException){
    return new ResponseEntity<String>(creditCardExistException.getMessage(), HttpStatus.OK);
}

    @ExceptionHandler(CreditCardInValidException.class)
    public ResponseEntity<String> handleCreditCardExistException(CreditCardInValidException creditCardInValidException){
        return new ResponseEntity<String>(creditCardInValidException.getMessage(), HttpStatus.OK);
    }
}
