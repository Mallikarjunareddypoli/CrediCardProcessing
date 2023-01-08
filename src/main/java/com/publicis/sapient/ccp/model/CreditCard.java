package com.publicis.sapient.ccp.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    @NotBlank(message = "Card Number may not be blank")
    public String ccNumber;
    @NotBlank(message = "Card Name may not be blank")
    public String ccName;
    public long ccBalance;
    @Positive
    public long ccLimit;

    public CreditCard(String ccNumber, String ccName, long ccBalance, long cclimit) {
        this.ccNumber = ccNumber;
        this.ccName = ccName;
        this.ccBalance = ccBalance;
        this.ccLimit = cclimit;
    }
}
