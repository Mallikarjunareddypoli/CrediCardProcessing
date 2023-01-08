package com.publicis.sapient.ccp.services;

import com.publicis.sapient.ccp.model.CreditCard;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CreditCardService {
    boolean addCreditCard(CreditCard creditCard);
    List<CreditCard> getAllCreditCards();
}
