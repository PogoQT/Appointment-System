package com.example.serviceImpl;

import com.example.entities.Provider;
import com.example.repository.ProviderRepository;
import com.example.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    private ProviderRepository providerRepo;

    @Override
    public List<Provider> getAllProvider() {
        return providerRepo.findAll();
    }

    @Override
    public void deleteProviderById(int providerID) {
        providerRepo.deleteById(providerID);
    }

    @Override
    public Provider getProviderbyId(int providerID) {
        return providerRepo.findById(providerID).get();
    }

    @Override
    public long countProviders() {
        return providerRepo.count();
    }

    @Override
    public Optional<Provider> getProviderByServiceID(int service_id) {
        return providerRepo.findByServiceID(service_id);
    }
}
