package com.example.controller;

import com.example.entities.Provider;
import com.example.entities.Services;
import com.example.entities.User;
import com.example.repository.ProviderRepository;
import com.example.repository.UserRepository;
import com.example.service.ProviderService;
import com.example.service.ServicesService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ProviderRepository providerRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ServicesService serviceServ;

    @Autowired
    private ProviderService providerSer;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("services", serviceServ.getAllService());
        model.addAttribute("providers", providerSer.getAllProvider());
        return "index";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") User ur, @RequestParam MultipartFile userImg, HttpSession session) {
        ur.setPassword(passwordEncoder.encode(ur.getPassword()));
        ur.setUserRole("ROLE_USER");
        ur.setUserImage(userImg.getOriginalFilename());

        User saveUser = userRepo.save(ur);

        if(saveUser != null) {
            try {
                // Define the directory where you want to save the image
                String uploadDir = "/static/images";
                File saveFile = new File(uploadDir);

                // If the directory does not exist, create it
                if (!saveFile.exists()) {
                    saveFile.mkdirs();
                }

                // Save the file to the directory
                Path path = Paths.get(uploadDir + File.separator + userImg.getOriginalFilename());
                Files.copy(userImg.getInputStream(), path);
                session.setAttribute("message", "User has been successfully registered");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            session.setAttribute("message", "Something Went wrong");
        }

        return "redirect:/login";
    }

    @GetMapping("/signupProvider")
    public String signupProvider(Model model) {
        List<Services> services = serviceServ.getAllService();
        model.addAttribute("services", services);
        model.addAttribute("provider", new Provider());
        return "signupProvider";
    }

    @PostMapping("/registerProvider")
    public String registerProvider(@ModelAttribute("Provider") Provider provider,
                                   @RequestParam MultipartFile providerImg, HttpSession session) {
        provider.setPassword(passwordEncoder.encode(provider.getPassword()));
        provider.setProviderRole("ROLE_PROVIDER");
        provider.setProviderImage(providerImg.getOriginalFilename());
        provider.setService(provider.getService());

        Provider savedProvider = providerRepo.save(provider);

        if (savedProvider != null) {
            try {
                // Define the directory where you want to save the image
                String uploadDir = "/static/images";
                File saveFile = new File(uploadDir);

                // If the directory does not exist, create it
                if (!saveFile.exists()) {
                    saveFile.mkdirs();
                }

                // Save the file to the directory
                Path path = Paths.get(uploadDir + File.separator + providerImg.getOriginalFilename());
                Files.copy(providerImg.getInputStream(), path);
                session.setAttribute("message", "Service Provider has been successfully registered");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            session.setAttribute("message", "Something Went wrong");
        }

        return "redirect:/login";
    }


    @GetMapping("/login")
    public String handelLogin(){
        return "custom_login";
    }

}
