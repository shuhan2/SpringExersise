package com.thoughtworks.springExercise.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.springExercise.domain.Contact;
import com.thoughtworks.springExercise.domain.User;
import com.thoughtworks.springExercise.repository.Impl.UserRepositoryImpl;
import com.thoughtworks.springExercise.repository.Impl.UserStorage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class UserControllerTest {
    private MockMvc mockMvc ;
    private static final String WA_SI = "wa si";
    private static final int SIXTEEN = 16;
    private static final int PHONENUMBER = 2512371;
    private User user;
    private Contact contact;

    @BeforeEach
    void setUp() {
        mockMvc = standaloneSetup(new UserController()).build();
        UserStorage.clear();
        user = new User(5, "sjyuan", new HashMap<>());
        contact = new Contact(1, "zhang san", 123456,"female", 18);
        UserStorage.add(user);
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
        int originalSize = UserStorage.getUsers().size();
        mockMvc.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON_UTF8).
                content(new ObjectMapper().writeValueAsString(user))).
                andExpect(status().is(201)).
                andExpect(jsonPath("$.name").value("wang wu")).
                andExpect(jsonPath("$.id").value(3));
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        assertEquals(originalSize + 1, userRepository.findUsers().size());
    }

    @Test
    void should_update_user() throws Exception {
        User originalUser =  new User(1, "wang wu");
        UserStorage.add(originalUser);

        User updatedUser =  new User(1, "wang jin");
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        mockMvc.perform(put("/api/users/1").
                contentType(MediaType.APPLICATION_JSON_UTF8).
                content(new ObjectMapper().writeValueAsString(updatedUser))).
                andExpect(status().isOk()).
                andExpect(jsonPath("$.name").value("wang jin")).
                andExpect(jsonPath("$.id").value(1));

        assertEquals("wang jin", UserStorage.getById(1).getName());
    }

    @Test
    void should_delete_user() throws Exception {
        User originalUser =  new User(1, "wang wu");
        UserStorage.add(originalUser);
        int originalSize = UserStorage.getUsers().size();

        mockMvc.perform(delete("/api/users/1")).
                andExpect(status().is(204));

        assertEquals(originalSize - 1, UserStorage.getUsers().size());

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
        user.getContacts().put(contact.getId(), contact);
        mockMvc.perform(get("/api/users/5/contacts")).
                andExpect(status().is(200)).
                andExpect(jsonPath("$['1'].id").value(1)).
                andExpect(jsonPath("$['1'].name").value("zhang san")).
                andExpect(jsonPath("$['1'].phoneNumber").value(123456)).
                andExpect(jsonPath("$['1'].gender").value("female")).
                andExpect(jsonPath("$['1'].age").value(18));
    }


    @Test
    void should_update_contact_in_a_user() throws Exception {
        user.getContacts().put(contact.getId(), contact);
        Contact updatedContact = new Contact(1, "wa si",2512371, "female", 16 );

        mockMvc.perform(put("/api/users/5/contacts/1").
                contentType(MediaType.APPLICATION_JSON_UTF8).
                content(new ObjectMapper().writeValueAsString(updatedContact))).
                andExpect(status().is(200));

        assertEquals(WA_SI, UserStorage.getById(5).getContacts().get(1).getName());
        assertEquals(PHONENUMBER, UserStorage.getById(5).getContacts().get(1).getPhoneNumber());
        assertEquals(SIXTEEN, UserStorage.getById(5).getContacts().get(1).getAge());
    }

    @Test
    void should_delete_contact_in_a_user() throws Exception {
        user.getContacts().put(contact.getId(), contact);
        int originalSize = user.getContacts().size();
        mockMvc.perform(delete("/api/users/5/contacts/1")).
                andExpect(status().is(204));
        assertEquals(originalSize - 1, user.getContacts().size());
    }

    @Test
    void should_return_contact_with_user_name_is_sjyuan_and_contact_name_is_kgyang() throws Exception {
        Contact kgContact = new Contact(6, "kgyang", 2821171, "male", 18);

        user.getContacts().put(contact.getId(), kgContact);
        mockMvc.perform(get("/api/users/sjyuan/contacts/kgyang")).
                andExpect(status().is(200)).
                andExpect(jsonPath("$.id").value(6)).
                andExpect(jsonPath("$.name").value("kgyang")).
                andExpect(jsonPath("$.phoneNumber").value(2821171)).
                andExpect(jsonPath("$.gender").value("male")).
                andExpect(jsonPath("$.age").value(18));
    }

    @Test
    void should_return_404_when_can_not_find_contact() throws Exception {
        Contact kgContact = new Contact(6, "kgli", 2821171, "male", 18);

        user.getContacts().put(contact.getId(), kgContact);
        mockMvc.perform(get("/api/users/sjyuan/contacts/kgyang")).
                andExpect(status().is(404));

    }




    @AfterEach
    void teardown() {
        UserStorage.clear();
    }
}
