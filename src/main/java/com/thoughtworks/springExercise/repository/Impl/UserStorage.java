package com.thoughtworks.springExercise.repository.Impl;

import com.thoughtworks.springExercise.domain.User;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class UserStorage {
    static final Map<Integer, User> USERS = new HashMap<>();
    static {
        USERS.put(1, new User(1, "zhang san"));
        USERS.put(2, new User(2, "li si"));
    }

    public static void clear() {
        USERS.clear();
    }

    public static void add(User...users) {
        Arrays.stream(users).forEach(user -> USERS.put(user.getId(), user));
    }

    public static User getById(int id) {
        return USERS.get(1);
    }

    public static Map<Integer, User> getUsers() {
        return USERS;
    }
}
