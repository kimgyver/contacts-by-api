package com.jason.userContactInfo.services;

import com.jason.userContactInfo.models.UserContact;

public interface UserContactsService {
    public UserContact findById(String id);
    public UserContact[] findByName(String name);
}
