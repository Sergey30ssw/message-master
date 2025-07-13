package com.messagemaster.service;

import com.messagemaster.model.User;
import com.messagemaster.repository.UserRepository;
import com.messagemaster.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService; // Используем реализацию вместо интерфейса

    @Test
    public void testRegisterUser() {
        // Подготовка тестовых данных
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("testpass");
        user.setRole("USER");

        // Мокируем вызов репозитория
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Вызываем тестируемый метод
        User registeredUser = userService.registerUser(user);

        // Проверяем результаты
        assertNotNull(registeredUser);
        assertEquals("testuser", registeredUser.getUsername());
        assertEquals("testpass", registeredUser.getPassword());
        assertEquals("USER", registeredUser.getRole());

        // Проверяем, что метод save был вызван 1 раз
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testFindByUsername_UserExists() {
        // Подготовка тестовых данных
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("testpass");
        user.setRole("USER");

        // Мокируем вызов репозитория
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));

        // Вызываем тестируемый метод
        User foundUser = userService.findByUsername("testuser");

        // Проверяем результаты
        assertNotNull(foundUser);
        assertEquals("testuser", foundUser.getUsername());

        // Проверяем, что метод findByUsername был вызван 1 раз
        verify(userRepository, times(1)).findByUsername("testuser");
    }

    @Test
    public void testFindByUsername_UserNotExists() {
        // Мокируем вызов репозитория для несуществующего пользователя
        when(userRepository.findByUsername("unknown")).thenReturn(Optional.empty());

        // Вызываем тестируемый метод
        User foundUser = userService.findByUsername("unknown");

        // Проверяем, что вернулся null
        assertNull(foundUser);

        // Проверяем, что метод findByUsername был вызван 1 раз
        verify(userRepository, times(1)).findByUsername("unknown");
    }

    @Test
    public void testFindAll() {
        // Подготовка тестовых данных
        User user1 = new User();
        user1.setUsername("user1");
        User user2 = new User();
        user2.setUsername("user2");
        List<User> users = Arrays.asList(user1, user2);

        // Мокируем вызов репозитория
        when(userRepository.findAll()).thenReturn(users);

        // Вызываем тестируемый метод
        List<User> result = userService.findAll();

        // Проверяем результаты
        assertEquals(2, result.size());
        assertEquals("user1", result.get(0).getUsername());
        assertEquals("user2", result.get(1).getUsername());

        // Проверяем, что метод findAll был вызван 1 раз
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteUser() {
        // Вызываем тестируемый метод
        userService.deleteUser(1L);

        // Проверяем, что метод deleteById был вызван с правильным ID
        verify(userRepository, times(1)).deleteById(1L);
    }
}