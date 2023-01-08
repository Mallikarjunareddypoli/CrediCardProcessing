package com.publicis.sapient.ccp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.publicis.sapient.ccp.constants.CreditCardConstants;
import com.publicis.sapient.ccp.controllers.CreditCardProcessController;
import com.publicis.sapient.ccp.model.CreditCard;
import com.publicis.sapient.ccp.services.CreditCardService;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static com.publicis.sapient.ccp.utils.JsonUtlis.asJsonString;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CreditCardProcessController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CreditCreditCardControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CreditCardService creditCardService;
    @Test
    @Order(1)
    public void shouldAddCreditCard() throws Exception {
        CreditCard newCreditCreditCard =new CreditCard("5531006517734657",
                "Test",
                0,100);

        when(creditCardService.addCreditCard(any())).thenReturn(true);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/cc") .content(asJsonString(newCreditCreditCard))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(creditCardService).addCreditCard(Mockito.any(CreditCard.class));
    }
    @Test
    public void shouldReturnInvalidCreditCard() throws Exception {
        CreditCard newCreditCreditCard =new CreditCard("1111222233334424",
                "Test",
                0,100);
        when(creditCardService.addCreditCard(newCreditCreditCard)).thenReturn(false);
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post("/cc") .content(asJsonString(newCreditCreditCard))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andReturn();
        assertThat(result).isNotNull();
        MockHttpServletResponse response=result.getResponse();
        assertThat(response).isNotNull();
        assertThat(response.equals(CreditCardConstants.CREDIT_CARD_EXIST));
    }
    @Test
    @Order(2)
    public void shouldReturnAllCreditCards() throws Exception {
    List<CreditCard> creditCards =new ArrayList<CreditCard>();
    creditCards.add(new CreditCard("1111222233334444",
            "Test",
            0,100));
        creditCards.add(new CreditCard("5531006517734657",
                "Test",
                0,200));
        when(creditCardService.getAllCreditCards()).thenReturn(creditCards);

        this.mockMvc.perform(get("/cc"))
                .andExpect(status().isOk())
                .andExpect(response ->
                {
                    String jsonResp=response.getResponse().getContentAsString();
                    ObjectMapper objectMapper= new ObjectMapper();
                    List<CreditCard> actual=objectMapper.readValue(jsonResp,List.class);
                    assertEquals(creditCards.size(),actual.size());
                });
    }
    @Test
    @Order(3)
    public void shouldReturnExistCreditCards() throws Exception {
        CreditCard newCreditCreditCard = new CreditCard("5531006517734657",
                "Test",
                0, 100);
        when(creditCardService.addCreditCard(newCreditCreditCard)).thenReturn(false);
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post("/cc").content(asJsonString(newCreditCreditCard))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andReturn();
        assertThat(result).isNotNull();
        MockHttpServletResponse response = result.getResponse();
        assertThat(response).isNotNull();
        assertThat(response.equals(CreditCardConstants.CREDIT_CARD_EXIST));
    }
}
