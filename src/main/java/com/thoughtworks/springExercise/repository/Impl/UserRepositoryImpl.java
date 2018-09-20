package com.thoughtworks.springExercise.repository.Impl;

import com.thoughtworks.springExercise.domain.User;
import com.thoughtworks.springExercise.repository.UserRepository;

import java.util.Collection;

import static com.thoughtworks.springExercise.repository.Impl.UserStorage.USERS;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public Collection<User> findUsers() {

        return USERS.values();
    }

    @Override
    public User createUser(User user) {
        USERS.put(user.getId(), user);
        return user;
    }


}
