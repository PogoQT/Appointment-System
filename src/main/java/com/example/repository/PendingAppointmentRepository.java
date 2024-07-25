package com.example.repository;

import com.example.entities.PendingAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PendingAppointmentRepository extends JpaRepository<PendingAppointment, Integer> {

    @Query("SELECT pa from PendingAppointment pa where pa.provider.providerID = :providerID")
    List<PendingAppointment> findPendingAppointmentByProviderID(int providerID);
}