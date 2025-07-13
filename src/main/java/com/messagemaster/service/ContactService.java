package com.messagemaster.service;

import com.messagemaster.dto.ContactDTO;
import java.util.List;

public interface ContactService {
    ContactDTO createContact(ContactDTO contactDTO);
    List<ContactDTO> getUserContacts(Long userId);
    ContactDTO updateContact(Long id, ContactDTO contactDTO);
    void deleteContact(Long id);
}