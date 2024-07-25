package com.example.entities;

import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class KhaltiResponse {
    private String pidx;
    private String payment_url;
    private String expires_at;
    private String expires_in;
    private String user_fee;
}