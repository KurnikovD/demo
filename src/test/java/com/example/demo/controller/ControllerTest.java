package com.example.demo.controller;

import com.example.demo.service.DomainServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class ControllerTest {

    @Autowired
    Controller controller;
    @MockBean
    DomainServiceImpl service;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCorrectTopEmpty() throws Exception {
        Mockito.when(service.top(Mockito.anyInt())).thenReturn(Collections.emptyList());

        this.mockMvc.perform(get("/top/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));
    }

    @Test
    public void testCorrectTopIsNotEmpty() throws Exception {
        Mockito.when(service.top(Mockito.anyInt())).thenReturn(List.of("vk", "google"));

        this.mockMvc.perform(get("/top/2"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[\"vk\",\"google\"]")));
    }

    @Test
    void testAdd() throws Exception {
        this.mockMvc.perform(get("/add/ya.ru/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("")));

        Mockito.verify(service).add("ya.ru/", 1);
    }

    @Test
    void testFailedTopIfArgumentIsLetter() throws Exception {
        this.mockMvc.perform(get("/top/a"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Error! Is not a number")));
    }

    @Test
    void testFailedTopIfArgumentIsBellowThenZero() throws Exception {
        this.mockMvc.perform(get("/top/-1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Error! The number must be greater than 0")));
    }


}