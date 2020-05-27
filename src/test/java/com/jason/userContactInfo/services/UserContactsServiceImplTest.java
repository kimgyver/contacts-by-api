package com.jason.userContactInfo.services;

import com.jason.userContactInfo.models.UserContact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserContactsServiceImplTest {
    UserContactsServiceImpl userContactsService;
    String userIdExisting = "1";
    String userIdNotExisting = "100";

    String userNameExisting = "Samantha";
    String idOfSamantha = "3";
    String userNameNotExisting = "HERCULES";

    @BeforeEach
    void setUp() {
        userContactsService = new UserContactsServiceImpl();
    }

    @Test
    void findById() {
        // Existing
        UserContact contact = userContactsService.findById(userIdExisting);
        assertEquals(userIdExisting, contact.getId());

        // Not Existing
        UserContact contactNotExisting = userContactsService.findById(userIdNotExisting);
        assertEquals("-1 (Not Found)", contactNotExisting.getId());
    }

    @Test
    void findByName() {
        // Existing
        UserContact contact[] = userContactsService.findByName(userNameExisting);
        assertEquals(idOfSamantha, contact[0].getId());

        // Not Existing
        UserContact contactNotExisting[] = userContactsService.findByName(userNameNotExisting);
        assertEquals("-1 (Not Found)", contactNotExisting[0].getId());
    }
}