package com.thoughtworks.springExercise.repository;

import com.thoughtworks.springExercise.domain.User;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class UserStorage {
    public static Map<Integer, User> users = new HashMap<>();
    static {
        users.put(1, new User(1, "zhang san"));
        users.put(2, new User(2, "li si"));
    }

    public static void clear() {
        users.clear();
    }

    public static void add(User...user) {
        Arrays.stream(user).map(item -> users.put(users.size()+1,item));
    }
}
