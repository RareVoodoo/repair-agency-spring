package ua.testing.repairagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.testing.repairagency.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
