package com.example.config;

import com.example.entities.Provider;
import com.example.entities.User;
import com.example.repository.ProviderRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ProviderRepository providerRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         Optional<User> user = userRepo.findByUsername(username);
         if (user.isPresent()){
             return user.get();
         }else {
             Optional<Provider> provider = providerRepo.findByUsername(username);
             return provider.get();
         }
    }
}
