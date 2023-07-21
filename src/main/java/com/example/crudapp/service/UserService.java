package com.example.crudapp.service;

import com.example.crudapp.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    void setUser(User user);

    User getUser(Integer id);

    void deleteUser(Integer id);
}
