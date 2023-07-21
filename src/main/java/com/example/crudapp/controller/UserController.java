package com.example.crudapp.controller;

import com.example.crudapp.model.User;
import com.example.crudapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("")
    public List<User> list(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable Integer id){
        try {
            User user = userService.getUser(id);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }
        catch (NoSuchElementException exception){
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/")
    public ResponseEntity<?> add(@RequestBody User user) {
        try {
            userService.setUser(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping(path = "/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id,
                                    @RequestParam(required = false) String firstName,
                                    @RequestParam(required = false) String lastName) {
        try {
            User existUser = userService.getUser(id);

            if(firstName!= null && firstName.length()>0 && !Objects.equals(existUser.getFirstName(),firstName)){
                existUser.setFirstName(firstName);
            }
            if(lastName!= null && lastName.length()>0 && !Objects.equals(existUser.getLastName(),lastName)){
                existUser.setLastName(lastName);
            }
            userService.setUser(existUser);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        userService.deleteUser(id);
    }



}
