package com.publicis.sapient.ccp.controller;

import com.publicis.sapient.ccp.constants.CreditCardConstants;
import com.publicis.sapient.ccp.model.CreditCard;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    @Test
    public void shouldReturnAllCreditCards(){
        List ccList= this.restTemplate.getForObject(
                "http://localhost:"+port+"/cc",
                List.class);

        assertThat(ccList).isNotEmpty();
        assertThat(ccList.size()).isGreaterThan(0);
    }

    @Test
    public void shouldReturnInvalidCreditCard(){
        CreditCard addCreditCreditCard =new CreditCard("1111222233334445",
                "Test",
                0,100);
        String resultCreditCard=this.restTemplate.postForObject(
                "http://localhost:"+port+"/cc", addCreditCreditCard,String.class
        );
        assertThat(resultCreditCard).isNotNull();
        assertThat(CreditCardConstants.CREDIT_CARD_NUMBER_IN_VALID.equals(resultCreditCard));
    }
    @Test
    public void shouldCreateCreditCard(){
        CreditCard addCreditCard =new CreditCard("1111222233334444",
                "Test",
                0,100);
        CreditCard resultCreditCard=this.restTemplate.postForObject(
                "http://localhost:"+port+"/cc", addCreditCard,CreditCard.class
        );
        assertThat(resultCreditCard).isNotNull();
        assertThat(addCreditCard.ccNumber.equals(resultCreditCard.ccNumber));
        assertThat(addCreditCard.ccName.equals(resultCreditCard.ccName));
        assertThat(addCreditCard.ccBalance== resultCreditCard.ccBalance);
        assertThat(addCreditCard.ccLimit == resultCreditCard.ccLimit);
    }
    @Test
    public void shouldReturnExitCreditCard(){
        CreditCard addCreditCreditCard =new CreditCard("1111222233334444",
                "Test",
                0,100);
        String resultCreditCard=this.restTemplate.postForObject(
                "http://localhost:"+port+"/cc", addCreditCreditCard,String.class
        );
        assertThat(resultCreditCard).isNotNull();
        assertThat(CreditCardConstants.CREDIT_CARD_EXIST.equals(resultCreditCard));
    }
}
