package com.thoughtworks.springExercise.controller;

import com.thoughtworks.springExercise.domain.Contact;
import com.thoughtworks.springExercise.domain.User;
import com.thoughtworks.springExercise.repository.Impl.ConcatRepositoryImpl;
import com.thoughtworks.springExercise.repository.Impl.UserRepositoryImpl;
import com.thoughtworks.springExercise.repository.Impl.UserStorage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequestMapping("/api")
@RestController
public class UserController {
    @RequestMapping("/users")
    public Collection<User> findUsers(){
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        return userRepository.findUsers();
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        UserStorage.add(user);
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        return new ResponseEntity<>(userRepository.createUser(user), HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        return new ResponseEntity<>(userRepository.updateUser(id, user), HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable int id) {
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        userRepository.delete(id);
    }
    @PostMapping("/users/{userId}/contacts")
    public ResponseEntity<Contact> createContact(@PathVariable int userId, @RequestBody Contact contact){
        ConcatRepositoryImpl contactRepository = new ConcatRepositoryImpl();
        return new ResponseEntity<>(contactRepository.createContactForUser(userId , contact), HttpStatus.CREATED);
    }




}
