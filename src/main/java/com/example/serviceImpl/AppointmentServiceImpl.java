package com.example.serviceImpl;

import com.example.entities.Appointment;
import com.example.entities.PendingAppointment;
import com.example.repository.AppointmentRepository;
import com.example.repository.PendingAppointmentRepository;
import com.example.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private PendingAppointmentRepository pendingAppointmentRepo;

    @Autowired
    private AppointmentRepository appointmentRepo;


    @Override
    public void confirmAppointment(int appointmentID) {
        PendingAppointment pendingAppointment = pendingAppointmentRepo.findById(appointmentID).get();
        if (pendingAppointment != null){
            Appointment appointment = new Appointment();
            appointment.setFullname(pendingAppointment.getFullname());
            appointment.setContact(pendingAppointment.getContact());
            appointment.setEmail(pendingAppointment.getEmail());
            appointment.setAppointmentDate(pendingAppointment.getAppointmentDate());
            appointment.setAddress(pendingAppointment.getAddress());
            appointment.setStatus(pendingAppointment.getStatus());
            appointment.setPaid(pendingAppointment.getPaid());
            appointment.setProvider(pendingAppointment.getProvider());
            appointment.setEmployee(pendingAppointment.getEmployee());
            appointment.setUserID(pendingAppointment.getUserID());

            appointmentRepo.save(appointment);
        }
    }

    @Override
    public List<Appointment> getAppointmentByProviderID(int providerID) {
        return appointmentRepo.findAppointmentByProviderID(providerID);
    }

    @Override
    public void deliverAppointment(int appointmentID) {
        Appointment appointment = appointmentRepo.findById(appointmentID).get();
        if (appointment != null) {
            appointment.setStatus("Delivered");
            appointmentRepo.save(appointment);
        }
    }

    @Override
    public List<Appointment> getAppointmentByUserID(int userID) {
        return appointmentRepo.findAppointmentByUserID(userID);
    }
}
