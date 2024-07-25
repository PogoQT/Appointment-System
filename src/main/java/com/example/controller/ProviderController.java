package com.example.controller;

import com.example.entities.Employee;
import com.example.entities.Provider;
import com.example.repository.EmployeeRepository;
import com.example.repository.ProviderRepository;
import com.example.service.AppointmentService;
import com.example.service.EmployeeService;
import com.example.service.PendingAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/provider")
public class ProviderController {
    @Autowired
    private EmployeeRepository employeeRepo;

    @Autowired
    private ProviderRepository providerRepo;

    @Autowired
    private EmployeeService employeeSer;

    @Autowired
    private PendingAppointmentService pendingAppointmentSer;

    @Autowired
    private AppointmentService appointmentSer;

    @GetMapping("/dashboard")
    public String providerDashboard(Model model, Principal principal){

        // count employee by providerID
        String username = principal.getName();
        Provider byProviderName = providerRepo.findByProvidername(username).get();
        int providerID = byProviderName.getProviderID();
        long employeeCount = employeeSer.countAllEmployeesByProviderID(providerID);
        model.addAttribute("employeeCount", employeeCount);

        return "/provider/dashboard";
    }

    @GetMapping("/employee")
    public String getEmployees(Model model, Principal principal){
        String username = principal.getName();
        Provider byProviderName = providerRepo.findByProvidername(username).get();
        int providerID = byProviderName.getProviderID();

        model.addAttribute("employee", employeeSer.getAllEmployeesByProviderID(providerID));
        return "/provider/employeeDetails";
    }

    @GetMapping("/addEmployee")
    public String addEmployee(Model model){
        model.addAttribute("employee", new Employee());
        return "/provider/addEmployee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee, Principal principal){
        String username = principal.getName();
        Provider byProviderName = providerRepo.findByProvidername(username).get();
        int providerID = byProviderName.getProviderID();

        employee.setProviderID(providerID);

        employeeRepo.save(employee);

        return "redirect:/provider/employee";
    }

    @GetMapping("/employee/delete/{employeeID}")
    public String deleteEmployee(@PathVariable("employeeID") int employeeID){
        employeeSer.deleteEmployeeById(employeeID);
        return "redirect:/provider/employee";
    }

    @GetMapping("/employee/update/{employeeID}")
    public String updateEmployee(@PathVariable("employeeID") int employeeID, Model model){
        Optional<Employee> employee = employeeSer.getEmployeeByID(employeeID);

        if(employee.isPresent()) {
            model.addAttribute("employee", employee.get());
            return "/provider/updateEmployee";
        }else
            return "404";
    }

    @GetMapping("/notification")
    public String getNotification(Model model, Principal principal){
        String username = principal.getName();
        Provider byProviderName = providerRepo.findByProvidername(username).get();
        int providerID = byProviderName.getProviderID();

        model.addAttribute("pendingAppointment", pendingAppointmentSer.getPendingAppointmentByProviderID(providerID));
        return "provider/bookingNotification";
    }

    @GetMapping("/confirmAppointment/{appointmentID}")
    public String saveAppointment(@PathVariable("appointmentID") int appointmentID){
        appointmentSer.confirmAppointment(appointmentID);

        pendingAppointmentSer.DeleteByAppointmentID(appointmentID);

        return "redirect:/provider/notification";
    }

    @GetMapping("/deleteAppointment/{appointmentID}")
    public String cancelAppointment(@PathVariable("appointmentID") int appointmentID){
        pendingAppointmentSer.DeleteByAppointmentID(appointmentID);
        return "redirect:/provider/notification";
    }

    @GetMapping("/appointment")
    public String getAppointment(Model model, Principal principal){
        String username = principal.getName();
        Provider byProviderName = providerRepo.findByProvidername(username).get();
        int providerID = byProviderName.getProviderID();

        model.addAttribute("appointment", appointmentSer.getAppointmentByProviderID(providerID));
        return "/provider/appointmentDetails";
    }

    @GetMapping("/deliver/{appointmentID}")
    public String deliveredAppointment(@PathVariable("appointmentID") int appointmentID){
        appointmentSer.deliverAppointment(appointmentID);
        return "redirect:/provider/appointment";
    }

}