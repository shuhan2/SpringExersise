package com.thoughtworks.springExercise.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.springExercise.domain.Contact;
import com.thoughtworks.springExercise.domain.User;
import com.thoughtworks.springExercise.repository.Impl.UserRepositoryImpl;
import com.thoughtworks.springExercise.repository.Impl.UserStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class ConcatControllerTest {
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc =  standaloneSetup(new UserController()).build();
        UserStorage.clear();
    }

    @Test
    void should_create_concat_with_user_id_is_5() throws Exception {
        Contact concat = new Contact(1, "zhang san", 123456,"female", 18);
        User user = new User(5, "zhang lan", new ArrayList<>());
        UserStorage.add(user);
        int originalConcatSize = user.getContacts().size();
        mockMvc.perform(post("/api/users/5/contacts").
                contentType(MediaType.APPLICATION_JSON_UTF8).
                content(new ObjectMapper().writeValueAsString(concat))).
                andExpect(status().is(201)).
                andExpect(jsonPath("$.id").value(1)).
                andExpect(jsonPath("$.name").value("zhang san")).
                andExpect(jsonPath("$.phoneNumber").value(123456)).
                andExpect(jsonPath("$.gender").value("female")).
                andExpect(jsonPath("$.age").value(18));

        assertEquals(originalConcatSize + 1, user.getContacts().size());
    }

    @Test
    void should_() {
    }
}
