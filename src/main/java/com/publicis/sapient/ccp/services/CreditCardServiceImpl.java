package com.publicis.sapient.ccp.services;

import com.publicis.sapient.ccp.model.CreditCard;
import com.publicis.sapient.ccp.repositories.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditCardServiceImpl implements CreditCardService {
    @Autowired
    private CreditCardRepository cardRepository;
    @Override
    public boolean addCreditCard(CreditCard creditCard) {
        CreditCard recordExist=cardRepository.findByCcNumber(creditCard.getCcNumber());
        if(recordExist==null){
            cardRepository.save(creditCard);
            return true;
        }else{
            return false;
        }

    }

    @Override
    public List<CreditCard> getAllCreditCards() {
        return cardRepository.findAll();
    }
}
