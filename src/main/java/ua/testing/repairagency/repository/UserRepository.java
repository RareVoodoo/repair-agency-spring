package ua.testing.repairagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.testing.repairagency.entity.User;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
