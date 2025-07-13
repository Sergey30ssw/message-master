package com.messagemaster.controller;

import com.messagemaster.dto.ContactDTO;
import com.messagemaster.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
@RequiredArgsConstructor
public class ContactController {
    private final ContactService contactService;

    @PostMapping
    public ContactDTO createContact(@RequestBody ContactDTO contactDTO) {
        return contactService.createContact(contactDTO);
    }

    @GetMapping("/user/{userId}")
    public List<ContactDTO> getUserContacts(@PathVariable Long userId) {
        return contactService.getUserContacts(userId);
    }

    @PutMapping("/{id}")
    public ContactDTO updateContact(@PathVariable Long id, @RequestBody ContactDTO contactDTO) {
        return contactService.updateContact(id, contactDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
    }
}