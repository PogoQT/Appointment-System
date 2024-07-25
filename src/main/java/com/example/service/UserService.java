package com.example.service;



import com.example.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public Optional<User> getUserById(int userID);

    public List<User> getAllUsersByUserRole();

    public void deleteUserById(int userID);

    public long countUsers();
}
