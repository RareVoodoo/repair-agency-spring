package ua.testing.repairagency.repository;

import org.springframework.data.repository.CrudRepository;
import ua.testing.repairagency.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User getUserByUsername(String username);
}
