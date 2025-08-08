package ru.kovorot.user.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kovorot.user.service.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}