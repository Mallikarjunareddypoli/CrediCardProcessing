package com.publicis.sapient.ccp.exceptions;

public class CreditCardExistException extends Exception{
    public CreditCardExistException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "CreditCardExistException{}";
    }
}
