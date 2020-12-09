package com.city.controller;

import com.city.service.CitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CitiesController.class)
public class CitiesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CitiesService cityConnectionServiceImpl;

    @DisplayName("Two test cities are Boston, Philadelphia")
    @Test
    public void validCityTest() throws Exception {
        when(cityConnectionServiceImpl.isConnected("Boston", "Philadelphia")).thenReturn("Yes");
        RequestBuilder request = MockMvcRequestBuilders
                .get("/connected")
                .param("origin", "Boston")
                .param("destination", "Philadelphia")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Yes"))
                .andReturn();
    }

    @DisplayName("Two cities are Chicago and Newark")
    @Test
    public void inValidCityTest() throws Exception {
        when(cityConnectionServiceImpl.isConnected("Chicago", "Newark")).thenReturn("Yes");
        RequestBuilder request = MockMvcRequestBuilders
                .get("/connected")
                .param("origin", "Chicago")
                .param("destination", "Newark")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Yes"))
                .andReturn();
    }

    @DisplayName("Empty cities, expected No")
    @Test
    public void emptyInputTest() throws Exception {
        when(cityConnectionServiceImpl.isConnected("", "")).thenReturn("No");
        RequestBuilder request = MockMvcRequestBuilders
                .get("/connected")
                .param("origin", "")
                .param("destination", "")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("No"))
                .andReturn();
    }
}

