package com.example.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "services")
public class Services {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_Id")
    private int service_Id;

    @Column(name = "service_name")
    private String service_name;

    public int getService_Id() {
        return service_Id;
    }

    public void setService_Id(int service_Id) {
        this.service_Id = service_Id;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }
}
