package com.example.newMock.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RequestDTO {

    private String rqUID;
    private String clientId;
    private String account;
    private String currency;
    private String balance;
    private String maxLimit;

}

//        "rqUID": "79dgtf565j8158f64gt4",
//        "clientId": "8050000000000000000",
//        "account": "80500000000000000001",
//        "currency": "US",
//        "balance": "1400.00",
//        "maxLimit": "2000.00"

//"rqUID": "58dgtf565j8547f64ke7",
//        "clientId": "3050000000000000000",
//        "account": "30500000000000000001",
//        "openDate": "2020-01-01",
//        "closeDate": "2025-01-01"