package com.example.service;

import com.example.entities.Provider;

import java.util.List;
import java.util.Optional;

public interface ProviderService {

    public List<Provider> getAllProvider();

    public void deleteProviderById(int providerID);

    public Provider getProviderbyId(int providerID);

    public long countProviders();

    public Optional<Provider> getProviderByServiceID(int service_id);
}
