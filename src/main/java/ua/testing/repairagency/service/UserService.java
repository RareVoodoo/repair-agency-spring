package ua.testing.repairagency.service;



import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.testing.repairagency.dto.UserDTO;
import ua.testing.repairagency.entity.Authority;
import ua.testing.repairagency.entity.User;
import ua.testing.repairagency.region.NameTransliteration;
import ua.testing.repairagency.repository.AuthorityRepository;
import ua.testing.repairagency.repository.UserRepository;



@Service
public class UserService {


    private static final String USER_ROLE = "ROLE_USER";

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final AuthorityRepository authorityRepository;

    private NameTransliteration nameTransliteration = new NameTransliteration();

    public UserService(AuthorityRepository authorityRepository, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Transactional
    public User registerNewUserAccount(UserDTO accountDto) {
        authorityRepository.save(Authority.builder()
                .authority(USER_ROLE)
                .username(accountDto.getUsername())
                .build());

        return userRepository.save(User.builder()
                .nameEn(nameTransliteration.transliterate(NameTransliteration.enLocale,accountDto.getNameEn()))
                .nameUa(nameTransliteration.transliterate(NameTransliteration.uaLocale,accountDto.getNameEn()))
                .username(accountDto.getUsername())
                .password(passwordEncoder.encode(accountDto.getPassword()))
                .enabled(true)
                .build());

    }

    @Transactional
    public User registerNewUserAccountWithCustomRole(UserDTO accountDto,String role) {
        authorityRepository.save(Authority.builder()
                .authority(role)
                .username(accountDto.getUsername())
                .build());

        return userRepository.save(User.builder()
                .nameEn(nameTransliteration.transliterate(NameTransliteration.enLocale,accountDto.getNameEn()))
                .nameUa(nameTransliteration.transliterate(NameTransliteration.uaLocale,accountDto.getNameEn()))
                .username(accountDto.getUsername())
                .password(passwordEncoder.encode(accountDto.getPassword()))
                .enabled(true)
                .build());
    }
}
