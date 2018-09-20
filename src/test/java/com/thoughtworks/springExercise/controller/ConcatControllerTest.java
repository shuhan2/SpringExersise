package com.thoughtworks.springExercise.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.springExercise.domain.Contact;
import com.thoughtworks.springExercise.domain.User;
import com.thoughtworks.springExercise.repository.Impl.UserStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class ConcatControllerTest {
    public static final String WA_SI = "wa si";
    public static final int SIXTEEN = 16;
    public static final int PHONENUMBER = 2512371;
    private MockMvc mockMvc;
    private User user;
    private Contact contact;
    @BeforeEach
    void setUp() {
        mockMvc =  standaloneSetup(new UserController()).build();
        UserStorage.clear();
        contact = new Contact(1, "zhang san", 123456,"female", 18);
        user = new User(5, "zhang lan", new HashMap<>());
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

        assertTrue(WA_SI.equals(UserStorage.getById(5).getContacts().get(1).getName()));
        assertTrue(PHONENUMBER == UserStorage.getById(5).getContacts().get(1).getPhoneNumber());
        assertTrue(SIXTEEN == (UserStorage.getById(5).getContacts().get(1).getAge()));
    }

    @Test
    void should_delete_contact_in_a_user() throws Exception {
        user.getContacts().put(contact.getId(), contact);
        int originalSize = user.getContacts().size();
        mockMvc.perform(delete("/api/users/5/contacts/1")).
                andExpect(status().is(204));
        assertEquals(originalSize - 1, user.getContacts().size());


    }
}
