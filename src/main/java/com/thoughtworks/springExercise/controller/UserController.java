package com.thoughtworks.springExercise.controller;

import com.thoughtworks.springExercise.domain.User;
import com.thoughtworks.springExercise.repository.Impl.UserRepositoryImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RequestMapping("/api")
@RestController
public class UserController {
    @RequestMapping("/users")
    public Collection<User> findUsers(){
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        return userRepository.findUsers();
    }



}
