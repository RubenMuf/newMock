package com.example.newMock.controller;

import com.example.newMock.model.RequestDTO;
import com.example.newMock.model.ResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.math.BigDecimal;


@RestController // анатация чтобы видеть классы
public class MainController {

    private Logger log = LoggerFactory.getLogger(MainController.class);

    ObjectMapper mapper = new ObjectMapper(); // для логирования заглушки

//    RequestDTO requestDTO; пример при объяснении

    // метод
    @PostMapping(
            value = "/info/postBalances",// сервер порт и адрес куда стучимся
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Object postBalances(@RequestBody RequestDTO requestDTO){ // метод получения тело запроса
        try {
            String clientId = requestDTO.getClientId(); // получаем значение в переменную из тела
            char firstDigit = clientId.charAt(0);
            BigDecimal maxLimit; // переменная для использования в логике
            String valuta = "";
            String rqUID = requestDTO.getRqUID();
//            System.out.println(maxLimit);


            if (firstDigit == '8') {
                maxLimit = new BigDecimal(2000);
                valuta = "US";
            } else if (firstDigit == '9') {
                maxLimit = new BigDecimal(1000);
                valuta = "EU";
            } else {
                maxLimit = new BigDecimal(10000);
                valuta = "RUB";
            }

            ResponseDTO responseDTO = new ResponseDTO();

//            RequestDTO requestDTO1 = new RequestDTO(rqUID,)

            responseDTO.setRqUID(rqUID);
            responseDTO.setClientId(clientId);
            responseDTO.setAccount(requestDTO.getAccount());
            responseDTO.setCurrency(valuta);
            responseDTO.setBalance(new BigDecimal((int)(Math.random() * maxLimit.intValue()) + 1));
            responseDTO.setMaxLimit(maxLimit);

            // логирование заглушки
            log.info("********** RequestDTO **********" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestDTO)); // mapper преобарзует в стрингу
            log.info("********** ResponseDTO **********" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseDTO)); // mapper преобарзует в стрингу

            return responseDTO;
        } catch (Exception e) {
//            throw new RuntimeException(e); // так тоже можно, но ниже способ проверенный
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
