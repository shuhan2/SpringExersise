package com.thoughtworks.springExercise;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class SortedPeopleTest {
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = standaloneSetup(new SortedPeople()).build();
    }

    @Test
    void should_return_sorted_people_when_input_valid_data() throws Exception {
        mockMvc.perform(get("/api/sortedPeople").
                param("names", "zhang san, li si").
                param("ages", "18, 16")).
                andExpect(status().isOk()).
                andExpect(jsonPath("$['zhang san'].name").value("zhang san")).
                andExpect(jsonPath("$['zhang san'].age").value(18)).
                andExpect(jsonPath("$['li si'].name").value("li si")).
                andExpect(jsonPath("$['li si'].age").value(16));
    }

    @Test
    void should_return_400_when_input_inValid_data() throws Exception {
        mockMvc.perform(get("/api/sortedPeople").
                param("names", "123").
                param("ages", "-1,23")).
                andExpect(status().is(400));
    }




}
