package com.example.controller;

import com.example.entities.Services;
import com.example.entities.User;
import com.example.service.EmployeeService;
import com.example.service.ProviderService;
import com.example.service.ServicesService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ServicesService serviceSer;

    @Autowired
    private ProviderService providerSer;

    @Autowired
    private UserService userSer;

    @Autowired
    private EmployeeService employeeSer;

    @GetMapping("/admindashboard")
    public String adminDashboard(Model model){
        long serviceCount = serviceSer.countServices();
        model.addAttribute("serviceCount", serviceCount);

        long providerCount = providerSer.countProviders();
        model.addAttribute("providerCount", providerCount);

        long userCount = userSer.countUsers();
        model.addAttribute("userCount", userCount);

        long employeeCount = employeeSer.countEmployees();
        model.addAttribute("employeeCount", employeeCount);
        return "/admin/admindashboard";
    }

    @GetMapping("/service")
    public String getServices(Model model) {
        model.addAttribute("service", serviceSer.getAllService());
        return "/admin/serviceDetails";
    }

    @GetMapping("/addService")
    public String addServices(Model model) {
        model.addAttribute("service", new Services());
        return "/admin/addService";
    }

    @PostMapping("/saveService")
    public String saveServices(@ModelAttribute("service") Services serv) {
        this.serviceSer.addService(serv);
        return "redirect:/admin/service";
    }

    @GetMapping("/service/delete/{service_id}")
    public String deleteService(@PathVariable("service_id") int service_Id) {
        serviceSer.deleteServiceById(service_Id);
        return "redirect:/admin/service";
    }

    @GetMapping("/service/update/{service_id}")
    public String updateService(@PathVariable("service_id") int service_id, Model model) {
        Optional<Services> services = serviceSer.getServiceById(service_id);

        if(services.isPresent()) {
            model.addAttribute("service", services.get());
            return "/admin/updateService";
        }else
            return "404";
    }

    @GetMapping("/profile/{userID}")
    public String updateProfile(@PathVariable("userID") int userID, Model model){
        Optional<User> user= userSer.getUserById(userID);
        if(user.isPresent()) {
            model.addAttribute("user", user.get());
            return "/admin/profile";
        }else
            return "404";
    }

    @GetMapping("/provider")
    public String getProviders(Model model){
        model.addAttribute("provider", providerSer.getAllProvider());
        return "admin/providerDetails";
    }

    @GetMapping("/provider/delete/{providerID}")
    public String deleteProvider(@PathVariable("providerID") int providerID){
        providerSer.deleteProviderById(providerID);
        return "redirect:/admin/provider";
    }

    @GetMapping("/user")
    public String getUsers(Model model){
        model.addAttribute("user", userSer.getAllUsersByUserRole());
        return "admin/userDetails";
    }

    @GetMapping("/user/delete/{userID}")
    public String deleteUser(@PathVariable("userID") int userID){
        userSer.deleteUserById(userID);
        return "redirect:/admin/user";
    }
}
