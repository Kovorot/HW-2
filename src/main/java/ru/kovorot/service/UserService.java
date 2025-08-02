package ru.kovorot.service;

import ru.kovorot.model.User;
import java.util.List;
import java.util.Optional;

/**
 * Простой сервис-слой для демонстрации к ДЗ.
 */
public interface UserService {

    Long createUser(User user);

    Optional<User> getUserById(Long id);

    List<User> getAllUsers();

    void updateUser(User user);

    void deleteUser(Long id);
}