package ua.testing.repairagency.repository;

import org.springframework.data.repository.CrudRepository;
import ua.testing.repairagency.entity.Authority;
import ua.testing.repairagency.entity.User;

import java.util.Optional;

public interface AuthorityRepository extends CrudRepository<Authority, Long> {
    Authority getAuthorityByUsername(String username);
}
