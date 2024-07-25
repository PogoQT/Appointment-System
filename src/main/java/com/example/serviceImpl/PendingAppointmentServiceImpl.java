package com.example.serviceImpl;

import com.example.entities.PendingAppointment;
import com.example.repository.PendingAppointmentRepository;
import com.example.service.PendingAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PendingAppointmentServiceImpl implements PendingAppointmentService {

    @Autowired
    private PendingAppointmentRepository pendingAppointmentRepo;
    @Override
    public List<PendingAppointment> getPendingAppointmentByProviderID(int providerID) {
        return pendingAppointmentRepo.findPendingAppointmentByProviderID(providerID);
    }

    @Override
    public void DeleteByAppointmentID(int appointmentID) {
        pendingAppointmentRepo.deleteById(appointmentID);
    }
}
