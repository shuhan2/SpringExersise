package com.thoughtworks.springExercise.repository.Impl;

import com.thoughtworks.springExercise.domain.User;
import com.thoughtworks.springExercise.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

import static com.thoughtworks.springExercise.repository.UserStorage.users;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public Collection<User> findUsers() {
        return users.values();
    }


}
