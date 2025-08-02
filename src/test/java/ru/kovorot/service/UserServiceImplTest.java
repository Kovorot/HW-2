package ru.kovorot.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.kovorot.dao.UserDao;
import ru.kovorot.model.User;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void createUserTest() {
        User user = new User();
        user.setName("Mock User");
        when(userDao.create(user)).thenReturn(1L);

        Long id = userService.createUser(user);
        assertEquals(1L, id);
        verify(userDao, times(1)).create(user);
    }

    @Test
    void getUserByIdTest() {
        User user = new User();
        user.setId(1L);
        when(userDao.findById(1L)).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUserById(1L);
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void getAllUsersTest() {
        when(userDao.findAll()).thenReturn(List.of(new User(), new User()));

        List<User> users = userService.getAllUsers();
        assertEquals(2, users.size());
    }

    @Test
    void updateUserTest() {
        User user = new User();
        user.setId(1L);
        doNothing().when(userDao).update(user);

        userService.updateUser(user);
        verify(userDao, times(1)).update(user);
    }

    @Test
    void deleteUserTest() {
        doNothing().when(userDao).delete(1L);

        userService.deleteUser(1L);
        verify(userDao, times(1)).delete(1L);
    }
}