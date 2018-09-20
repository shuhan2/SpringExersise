package com.thoughtworks.springExercise.repository;

import com.thoughtworks.springExercise.domain.User;

import java.util.Collection;

public interface UserRepository {
    Collection<User> findUsers();

    User createUser(User user);

    User updateUser(int id, User user);

    User findById(int id);
}
