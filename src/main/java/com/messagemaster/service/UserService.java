package com.messagemaster.service;

import com.messagemaster.model.User;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface UserService {
    User registerUser(User user);
    User findByUsername(String username);
    List<User> findAll();
    void deleteUser(Long id);
}
