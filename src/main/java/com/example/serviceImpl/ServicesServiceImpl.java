package com.example.serviceImpl;

import com.example.entities.Services;
import com.example.repository.ServicesRepository;
import com.example.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ServicesServiceImpl implements ServicesService {

    @Autowired
    private ServicesRepository serviceRepo;

    @Override
    public void addService(Services services) {
        serviceRepo.save(services);
    }

    @Override
    public List<Services> getAllService() {

        return serviceRepo.findAll();
    }

    @Override
    public void deleteServiceById(int service_Id) {
        serviceRepo.deleteById(service_Id);
    }

    @Override
    public Optional<Services> getServiceById(int service_Id) {
        return serviceRepo.findById(service_Id);
    }

    @Override
    public long countServices() {
        return serviceRepo.count();
    }
}
