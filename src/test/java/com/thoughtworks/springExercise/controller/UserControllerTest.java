package com.thoughtworks.springExercise.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.springExercise.domain.User;
import com.thoughtworks.springExercise.repository.Impl.UserRepositoryImpl;
import com.thoughtworks.springExercise.repository.Impl.UserStorage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class UserControllerTest {
    private MockMvc mockMvc ;

    @BeforeEach
    void setUp() {
        mockMvc = standaloneSetup(new UserController()).build();
        UserStorage.clear();
    }

    @Test
    void should_return_user_when_input_valid_id() throws Exception {
        User user = new User(1, "zhang san");
        User user1 = new User(2, "li si");
        UserStorage.add (user,user1);
        mockMvc.perform(get("/api/users")).
                andExpect(status().isOk()).
                andExpect(jsonPath("$[0].name").value("zhang san"));

    }

    @Test
    void should_return_user_when_input_valid_data() throws Exception {
        mockMvc.perform(get("/api/users")).
                andExpect(status().isOk());
    }

    @Test
    void should_create_user() throws Exception {
        User user =  new User(3, "wang wu");
        mockMvc.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON_UTF8).
                content(new ObjectMapper().writeValueAsString(user))).
                andExpect(status().is(201)).
                andExpect(jsonPath("$.name").value("wang wu")).
                andExpect(jsonPath("$.id").value(3));
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        assertEquals(1, userRepository.findUsers().size());
    }

    @AfterEach
    void teardown() {
    }
}
