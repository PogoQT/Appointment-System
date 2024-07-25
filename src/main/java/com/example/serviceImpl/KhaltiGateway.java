package com.example.serviceImpl;

import com.example.entities.CustomerInfo;
import com.example.entities.KhaltiPayment;
import com.example.entities.KhaltiResponse;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.client.RestTemplate;

@Component
public class KhaltiGateway {

    public String KhaltiPayment(CustomerInfo customerInfo, int amount){


        String url = "https://a.khalti.com/api/v2/epayment/initiate/";
        amount*=100;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "key 695dac1a7ff143939ab30a73d69cd13e");

        // Create a Payment object
        KhaltiPayment payment = new KhaltiPayment();
        payment.setReturn_url("http://127.0.0.1:4545/user/bookings");
        payment.setWebsite_url("http://127.0.0.1:4545/user");
        payment.setAmount("1000");
        payment.setPurchase_order_id("Order01");
        payment.setPurchase_order_name("test");

        payment.setCustomer_info(customerInfo);

        // Convert Payment object to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String payload;
        try {
            payload = objectMapper.writeValueAsString(payment);
        }
        catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting Payment object to JSON", e);
        }

        System.out.println(payload);

        HttpEntity<String> requestEntity = new HttpEntity<>(payload, headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);
        System.out.println(responseEntity.getBody());
        KhaltiResponse khaltiResponse;
        try {
            khaltiResponse = objectMapper.readValue(responseEntity.getBody(),KhaltiResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return khaltiResponse.getPayment_url();
    }
}

