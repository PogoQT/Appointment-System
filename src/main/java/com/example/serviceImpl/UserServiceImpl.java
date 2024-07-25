package com.example.serviceImpl;

import com.example.entities.User;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;
    @Override
    public Optional<User> getUserById(int userID) {
        return userRepo.findById(userID);
    }

    @Override
    public List<User> getAllUsersByUserRole() {
        return userRepo.findAllUsersByUserRole();
    }

    @Override
    public void deleteUserById(int userID) {
        userRepo.deleteById(userID);
    }

    @Override
    public long countUsers() {
        return userRepo.count();
    }
}
