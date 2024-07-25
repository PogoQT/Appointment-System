package com.example.service;

import com.example.entities.Appointment;

import java.util.List;

public interface AppointmentService {

    public void confirmAppointment(int appointmentID);

    public List<Appointment> getAppointmentByProviderID(int providerID);

    public void deliverAppointment(int appointmentID);

    public List<Appointment> getAppointmentByUserID(int userID);
}
