package com.example.repository;

import com.example.entities.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProviderRepository extends JpaRepository<Provider, Integer> {
    Optional<Provider> findByUsername(String username);

    @Query("Select p from Provider p where p.username = :username")
    Optional<Provider> findByProvidername(@Param("username") String username);

    @Query("SELECT p from Provider p where p.service.service_Id = :service_id")
    Optional<Provider> findByServiceID(int service_id);
}
