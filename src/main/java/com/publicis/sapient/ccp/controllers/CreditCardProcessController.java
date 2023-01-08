package com.publicis.sapient.ccp.controllers;

import com.publicis.sapient.ccp.constants.CreditCardConstants;
import com.publicis.sapient.ccp.exceptions.CreditCardExistException;
import com.publicis.sapient.ccp.exceptions.CreditCardInValidException;
import com.publicis.sapient.ccp.model.CreditCard;
import com.publicis.sapient.ccp.services.CreditCardService;
import com.publicis.sapient.ccp.validators.CreditCardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000"} )
@RestController
public class CreditCardProcessController {

    @Autowired
    private CreditCardService crediCardService;

    @PostMapping("/cc")
    public ResponseEntity<CreditCard> addCreditCard(@RequestBody CreditCard creditCard) throws CreditCardExistException, CreditCardInValidException {
        if(CreditCardValidator.isValid(creditCard.getCcNumber())) {
            boolean status = crediCardService.addCreditCard(creditCard);
            if (!status)
                throw new CreditCardExistException(CreditCardConstants.CREDIT_CARD_EXIST);
        }else {
            throw new CreditCardInValidException(CreditCardConstants.CREDIT_CARD_NUMBER_IN_VALID);
        }
        return new ResponseEntity<>(creditCard, HttpStatus.CREATED);
    }

    @GetMapping("/cc")
    public ResponseEntity<List<CreditCard>> getAllCreditCards(){
        return new ResponseEntity<>(crediCardService.getAllCreditCards(), HttpStatus.OK);
    }
}
