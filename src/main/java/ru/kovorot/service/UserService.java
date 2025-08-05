package ru.kovorot.service;

import ru.kovorot.dto.UserDTO;

import java.util.List;

/**
 * Простой сервис-слой для демонстрации к ДЗ.
 */
public interface UserService {

    UserDTO createUser(UserDTO userDTO);

    UserDTO getUserById(Long id);

    List<UserDTO> getAllUsers();

    UserDTO updateUser(Long id, UserDTO userDTO);

    void deleteUser(Long id);
}