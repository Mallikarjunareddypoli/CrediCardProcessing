package com.publicis.sapient.ccp.exceptions;

public class CreditCardInValidException extends Exception{
    public CreditCardInValidException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "CreditCardInValidException{}";
    }
}
