package com.thoughtworks.springExercise.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class ConcatControllerTest {
    private MockMvc mockMvc;
    private User user;
    private Contact contact;
    @BeforeEach
    void setUp() {
        mockMvc =  standaloneSetup(new UserController()).build();
        UserStorage.clear();
        contact = new Contact(1, "zhang san", 123456,"female", 18);
        user = new User(5, "zhang lan", new ArrayList<>());
        UserStorage.add(user);
    }

    @Test
    void should_create_concat_with_user_id_is_5() throws Exception {

        int originalConcatSize = user.getContacts().size();
        mockMvc.perform(post("/api/users/5/contacts").
                contentType(MediaType.APPLICATION_JSON_UTF8).
                content(new ObjectMapper().writeValueAsString(contact))).
                andExpect(status().is(201)).
                andExpect(jsonPath("$.id").value(1)).
                andExpect(jsonPath("$.name").value("zhang san")).
                andExpect(jsonPath("$.phoneNumber").value(123456)).
                andExpect(jsonPath("$.gender").value("female")).
                andExpect(jsonPath("$.age").value(18));

        assertEquals(originalConcatSize + 1, user.getContacts().size());
    }

    @Test
    void should_get_contacts_in_user_id_is_5() throws Exception {
        user.getContacts().add(contact);
        mockMvc.perform(get("/api/users/5/contacts")).
                andExpect(status().is(200)).
                andExpect(jsonPath("$[0].id").value(1)).
                andExpect(jsonPath("$[0].name").value("zhang san")).
                andExpect(jsonPath("$[0].phoneNumber").value(123456)).
                andExpect(jsonPath("$[0].gender").value("female")).
                andExpect(jsonPath("$[0].age").value(18));

    }

}
