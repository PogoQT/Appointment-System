package com.example.entities;

import lombok.Data;

@Data
public class KhaltiPayment {
    private String return_url;
    private String website_url;
    private String amount;
    private String purchase_order_id;
    private String purchase_order_name;
    private CustomerInfo customer_info;
}
