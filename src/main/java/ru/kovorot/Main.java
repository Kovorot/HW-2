package ru.kovorot;

import ru.kovorot.dao.UserDao;
import ru.kovorot.dao.UserDaoImpl;
import ru.kovorot.model.User;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    private static final UserDao userDao = new UserDaoImpl();

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nРабота с пользователями");
            System.out.println("1. Создать пользователя");
            System.out.println("2. Найти пользователя по id");
            System.out.println("3. Список всех пользователей");
            System.out.println("4. Изменить данные пользователя");
            System.out.println("5. Удалить пользователя");
            System.out.println("0. Выход");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> createUser();
                case 2 -> findUserById();
                case 3 -> listAllUsers();
                case 4 -> updateUser();
                case 5 -> deleteUser();
                case 0 -> System.exit(0);
                default -> System.out.println("Некорректное действие");
            }
        }
    }

    private static void createUser() {
        User user = new User();
        System.out.print("Введите имя: ");
        user.setName(scanner.nextLine());

        System.out.print("Введите email: ");
        user.setEmail(scanner.nextLine());

        System.out.print("Введите возраст: ");
        user.setAge(scanner.nextInt());
        scanner.nextLine();

        Long id = userDao.create(user);
        System.out.println("Создан новый пользователь с id = " + id);
    }

    private static void findUserById() {
        System.out.print("Введите id пользователя: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Optional<User> user = userDao.findById(id);
        user.ifPresentOrElse(
                System.out::println,
                () -> System.out.println("Такой пользователь не найден")
        );
    }

    private static void listAllUsers() {
        List<User> users = userDao.findAll();

        if (users.isEmpty()) {
            System.out.println("Не найдено ни одного пользователя");
        } else {
            users.forEach(System.out::println);
        }
    }

    private static void updateUser() {
        System.out.print("Введите id пользователя для обновления данных: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Optional<User> optionalUser = userDao.findById(id);
        if (optionalUser.isEmpty()) {
            System.out.println("Пользователь не найден");
            return;
        }

        User user = optionalUser.get();
        System.out.print("Введите новое имя (" + user.getName() + "): ");
        user.setName(scanner.nextLine());

        System.out.print("Введите новый email (" + user.getEmail() + "): ");
        user.setEmail(scanner.nextLine());

        System.out.print("Введите новый возраст (" + user.getAge() + "): ");
        user.setAge(scanner.nextInt());
        scanner.nextLine();

        userDao.update(user);
        System.out.println("Данные пользователя обновлены");
    }

    private static void deleteUser() {
        System.out.print("Введите id пользователя для удаления: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        userDao.delete(id);
        System.out.println("Пользователь удален");
    }
}