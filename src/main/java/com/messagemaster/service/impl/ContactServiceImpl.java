package com.messagemaster.service.impl;

import com.messagemaster.dto.ContactDTO;
import com.messagemaster.model.Contact;
import com.messagemaster.model.User;
import com.messagemaster.repository.ContactRepository;
import com.messagemaster.repository.UserRepository;
import com.messagemaster.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;
    private final UserRepository userRepository;

    @Override
    public ContactDTO createContact(ContactDTO contactDTO) {
        User user = userRepository.findById(contactDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Contact contact = new Contact();
        contact.setName(contactDTO.getName());
        contact.setPhoneNumber(contactDTO.getPhoneNumber());
        contact.setUser(user);

        return convertToDTO(contactRepository.save(contact));
    }

    @Override
    public List<ContactDTO> getUserContacts(Long userId) {
        return contactRepository.findByUserId(userId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ContactDTO updateContact(Long id, ContactDTO contactDTO) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact not found"));

        contact.setName(contactDTO.getName());
        contact.setPhoneNumber(contactDTO.getPhoneNumber());

        return convertToDTO(contactRepository.save(contact));
    }

    @Override
    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }

    private ContactDTO convertToDTO(Contact contact) {
        return ContactDTO.builder()
                .id(contact.getId())
                .name(contact.getName())
                .phoneNumber(contact.getPhoneNumber())
                .userId(contact.getUser().getId())
                .build();
    }
}