package com.publicis.sapient.ccp.repositories;

import com.publicis.sapient.ccp.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard,Long> {

CreditCard findByCcNumber(String ccNumber);

}
