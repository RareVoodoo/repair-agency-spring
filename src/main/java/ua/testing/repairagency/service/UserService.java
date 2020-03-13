package ua.testing.repairagency.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.testing.repairagency.dto.UserDTO;
import ua.testing.repairagency.entity.Authority;
import ua.testing.repairagency.entity.User;
import ua.testing.repairagency.repository.AuthorityRepository;
import ua.testing.repairagency.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;


    public User registerNewUserAccount(UserDTO accountDto){
        User user = new User();
        Authority authority = new Authority();
        user.setUsername(accountDto.getUsername());
        user.setPassword(accountDto.getPassword());
        user.setEnabled(true);

        authority.setAuthority("ROLE_USER");
        authority.setUsername(user.getUsername());

        authorityRepository.save(authority);
        return userRepository.save(user);
    }
}
