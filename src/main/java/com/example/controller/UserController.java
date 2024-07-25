package com.example.controller;

import com.example.entities.*;
import com.example.repository.AppointmentRepository;
import com.example.repository.CommentRepository;
import com.example.repository.PendingAppointmentRepository;
import com.example.repository.UserRepository;
import com.example.service.AppointmentService;
import com.example.service.EmployeeService;
import com.example.service.ProviderService;
import com.example.service.ServicesService;
import com.example.serviceImpl.KhaltiGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ServicesService serviceServ;

    @Autowired
    private ProviderService providerServ;

    @Autowired
    private EmployeeService employeeServ;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PendingAppointmentRepository pendingAppointmentRepo;

    @Autowired
    private AppointmentService appointmentSer;

    @Autowired
    private KhaltiGateway khaltiGateway;

    @Autowired
    private AppointmentRepository appointmentRepo;

    @Autowired
    private CommentRepository commentRepo;

    @GetMapping("/homepage")
    public String userHomepage(Model model){
        model.addAttribute("services", serviceServ.getAllService());
        model.addAttribute("providers", providerServ.getAllProvider());
        return "/user/homepage";
    }

    @GetMapping("/book/{providerID}")
    public String getBookFormByProviderID(@PathVariable("providerID") int providerID, Model model, Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
        model.addAttribute("provider", providerServ.getProviderbyId(providerID));
        model.addAttribute("employees", employeeServ.getAllEmployeesByProviderID(providerID));
        model.addAttribute("pendingAppointment",new PendingAppointment());
        return "/user/bookingForm";
    }

    @PostMapping("/appoint")
    public String saveBookings(@ModelAttribute("pendingAppointment") PendingAppointment pendingAppointment,
                               @RequestParam int providerID, Principal principal){
        // For userID

        String username = principal.getName();
        User byUsername = userRepo.findByUsername(username).get();

        int userID = byUsername.getUserID();

        //For providerID
        Provider getProviderID = providerServ.getProviderbyId(providerID);

        if (getProviderID != null){
            Provider provider = getProviderID;
            pendingAppointment.setUserID(userID);
            pendingAppointment.setProvider(provider);
            pendingAppointment.setEmployee(pendingAppointment.getEmployee());
            pendingAppointment.setStatus("Pending");
            pendingAppointment.setPaid("NotPaid");

           pendingAppointmentRepo.save(pendingAppointment);

        }
        return "redirect:/user/homepage";
    }

    @GetMapping("/bookings")
    public String getBookings(Model model, Principal principal){

        String username = principal.getName();
        User byUsername = userRepo.findByUsername(username).get();

        int userID = byUsername.getUserID();

        model.addAttribute("bookings", appointmentSer.getAppointmentByUserID(userID));

        return "/user/bookingsDetails";
    }

    @GetMapping("/initiate-payment/{appointmentID}")
    public String initiatePayment(Principal principal, @PathVariable("appointmentID") int appointmentID) {
        String username = principal.getName();
        User byUsername = userRepo.findByUsername(username).get();

        Appointment appointment = appointmentRepo.findById(appointmentID).get();


        String customerName = byUsername.getUsername();
        String customerEmail = byUsername.getEmail();
         String customerContact = byUsername.getContact();

        // Create a CustomerInfo object with customer details
        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setName(customerName);
        customerInfo.setEmail(customerEmail);
        customerInfo.setPhone(customerContact);

        // Initiate payment with customer info and amount
        String paymentUrl = khaltiGateway.KhaltiPayment(customerInfo, 1000);

        if (appointment != null) {
            appointment.setPaid("Paid");
            appointmentRepo.save(appointment);
        }

        // Redirect user to the payment URL
        return "redirect:" + paymentUrl;
    }

    @GetMapping("/comment")
    public String Comment(Model model){
        model.addAttribute("Comment", new Comment());
        return "/user/commentForm";
    }

    @PostMapping("/post")
    public String PostComment(@RequestParam int providerID, Principal principal){
        String username = principal.getName();
        User byUsername = userRepo.findByUsername(username).get();

        int userID = byUsername.getUserID();


        Provider getProviderID = providerServ.getProviderbyId(providerID);
        if (getProviderID != null){
            Comment comment = new Comment();
            comment.setProvider(getProviderID);
            comment.setUserID(userID);

            commentRepo.save(comment);
        }

        return "redirect:/user/bookingsDetails";
    }

}
