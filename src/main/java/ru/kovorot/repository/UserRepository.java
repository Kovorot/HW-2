package ru.kovorot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kovorot.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}