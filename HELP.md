# Requirements
--------------------------------------------------------------
### "Add" will create a new credit card for a given name, card number, and limit
#### End Point : 
                http://localhost:8080/cc
#### API Method :
               POST
#### InPut Json:
                {
                "ccNumber" :"5531006517734657",
                "ccName" : "Apple",
                "ccLimit" : 200
                }

#### OutPut Json:
                {
                id": 1,
                "ccNumber": "5531006517734657",
                "ccName": "Apple",
                "ccBalance": 0,
                "ccLimit": 200
                }
--------------------------------------------------------------
### Card numbers should be validated using Luhn 10
#### End Point : 
                http://localhost:8080/cc
#### API Method :
               POST
#### InPut Json: 
                {
                "ccNumber" :"5531006517734658",
                "ccName" : "Apple",
                "ccLimit" : 200
                }

#### OutPut : 
          Credit Card Number is Invalid!
--------------------------------------------------------------
### "Get all"
#### End Point :
                http://localhost:8080/cc
#### API Method :
               GET
#### OutPut : 
            [
    {
        "id": 1,
        "ccNumber": "5531006517734657",
        "ccName": "Apple",
        "ccBalance": 0,
        "ccLimit": 200
    },
    {
        "id": 2,
        "ccNumber": "1111222233334444",
        "ccName": "Basket",
        "ccBalance": 0,
        "ccLimit": 900
    }
]
### Card numbers should be validated if already credit card number exist
#### End Point :
                http://localhost:8080/cc
#### API Method :
               POST
#### InPut Json:
                {
                "ccNumber" :"5531006517734658",
                "ccName" : "Apple",
                "ccLimit" : 200
                }

#### OutPut :
          Credit Card Number Exist!
--------------------------------------------------------------