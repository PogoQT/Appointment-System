package com.example.service;

import com.example.entities.Services;

import java.util.List;
import java.util.Optional;

public interface ServicesService {

    public  void addService(Services services);

    public List<Services> getAllService();

    public void deleteServiceById(int service_Id);

    public Optional<Services> getServiceById(int service_Id);

    public long countServices();
}
