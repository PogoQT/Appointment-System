package com.example.repository;

import com.example.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    @Query("SELECT a from Appointment a where a.provider.providerID = :providerID")
    List<Appointment> findAppointmentByProviderID(int providerID);

    @Query("SELECT a from Appointment a where a.userID =:userID")
    List<Appointment> findAppointmentByUserID(int userID);
}
