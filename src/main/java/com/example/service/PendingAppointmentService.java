package com.example.service;

import com.example.entities.PendingAppointment;

import java.util.List;

public interface PendingAppointmentService {

    public List<PendingAppointment> getPendingAppointmentByProviderID(int providerID);

    public void DeleteByAppointmentID(int appointmentID);
}
