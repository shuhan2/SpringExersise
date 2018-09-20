package com.thoughtworks.springExercise.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.thoughtworks.springExercise.domain.User;
import com.thoughtworks.springExercise.repository.UserStorage;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class UserControllerTest {
    MockMvc mockMvc ;

    @BeforeEach
    void setUp() {
        mockMvc = standaloneSetup(new UserController()).build();
    }

    @Test
    void should_return_user_when_input_valid_id() throws Exception {

        mockMvc.perform(get("/api/users")).
                andExpect(status().isOk()).
                andExpect(jsonPath("$[0].name").value("zhang san"));
    }

    @Test
    void should_return_user_when_input_valid_data() throws Exception {
        mockMvc.perform(get("/api/users")).
                andExpect(status().isOk());
    }


    @AfterEach
    void teardown() {
    }
}
