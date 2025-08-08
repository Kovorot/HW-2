package ru.kovorot.user.service.service;

import ru.kovorot.user.service.dto.UserDTO;

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