package com.jason.userContactInfo.controllers;

import com.jason.userContactInfo.models.UserContact;
import com.jason.userContactInfo.services.UserContactsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserContactsControllerTest {
    @Mock
    UserContactsService service;

    UserContactsController controller;
    MockMvc mockMvc;

    String userId = "1";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new UserContactsController(service);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void getContactById() throws Exception {
        UserContact contact = new UserContact();
        contact.setId(userId);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        when(service.findById(anyString())).thenReturn(contact);

        mockMvc.perform(get("/getusercontacts/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{'id':'" + userId + "'}"));  // should be object

        verify(service, times(1)).findById(anyString());
    }

    @Test
    void getContactsByName() throws Exception {
        UserContact[] contacts = new UserContact[1];
        UserContact contact = new UserContact();
        contact.setId(userId);
        contacts[0] = contact;

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        when(service.findByName(anyString())).thenReturn(contacts);

        mockMvc.perform(get("/getusercontacts/name/Thomson"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id':'" + userId + "'}]"));    // should be array

        verify(service, times(1)).findByName(anyString());
    }
}