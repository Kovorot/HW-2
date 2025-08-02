package ru.kovorot.dao;

import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.kovorot.model.User;
import ru.kovorot.util.HibernateUtils;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserDaoImplIntegrationTest {

    @Container
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    private static UserDao userDao;

    @BeforeAll
    static void setup() {
        System.setProperty("hibernate.connection.url", postgres.getJdbcUrl());
        System.setProperty("hibernate.connection.username", postgres.getUsername());
        System.setProperty("hibernate.connection.password", postgres.getPassword());
        userDao = new UserDaoImpl();
    }

    @AfterAll
    static void tearDown() {
        HibernateUtils.shutdown();
    }

    @Test
    @Order(1)
    void createUserTest() {
        User user = new User();
        user.setName("Test User");
        user.setEmail("test@example.com");
        user.setAge(30);

        Long id = userDao.create(user);
        assertNotNull(id);
    }

    @Test
    @Order(2)
    void findByIdTest() {
        Optional<User> user = userDao.findById(1L);
        assertTrue(user.isPresent());
        assertEquals("test@example.com", user.get().getEmail());
    }

    @Test
    @Order(3)
    void findAllTest() {
        List<User> users = userDao.findAll();
        assertFalse(users.isEmpty());
        assertEquals(1, users.size());
    }

    @Test
    @Order(4)
    void updateUserTest() {
        User user = userDao.findById(1L).get();
        user.setName("Updated Name");
        userDao.update(user);

        User updated = userDao.findById(1L).get();
        assertEquals("Updated Name", updated.getName());
    }

    @Test
    @Order(5)
    void deleteUserTest() {
        userDao.delete(1L);
        Optional<User> deleted = userDao.findById(1L);
        assertTrue(deleted.isEmpty());
    }
}