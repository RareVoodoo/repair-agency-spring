package ua.testing.repairagency.service;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.testing.repairagency.dto.UserDto;
import ua.testing.repairagency.entity.Authority;
import ua.testing.repairagency.entity.User;
import ua.testing.repairagency.region.transliteration.NameTransliteration;
import ua.testing.repairagency.repository.AuthorityRepository;
import ua.testing.repairagency.repository.UserRepository;
import ua.testing.repairagency.util.Constants;

import java.util.List;

@Component
@Service
public class UserService {


    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final AuthorityRepository authorityRepository;

    private NameTransliteration nameTransliteration = new NameTransliteration();

    public UserService(AuthorityRepository authorityRepository, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }


    /**
     * Adds user and user authority as record to the db
     *
     * @param accountDto user account object
     */
    @Transactional
    public void registerNewUserAccount(UserDto accountDto) {
        authorityRepository.save(Authority.builder()
                .authority(Constants.USER_ROLE)
                .username(accountDto.getUsername())
                .build());

        userRepository.save(User.builder()
                .nameEn(nameTransliteration.transliterate(Constants.EN_LOCALE, accountDto.getNameEn()))
                .nameUa(nameTransliteration.transliterate(Constants.UA_LOCALE, accountDto.getNameEn()))
                .username(accountDto.getUsername())
                .password(passwordEncoder.encode(accountDto.getPassword()))
                .enabled(true)
                .build());

    }

    /**
     * Adds user and user's custom authority as record to the db
     *
     * @param accountDto user account object
     * @param authority  user's authority
     */
    @Transactional
    public void registerNewUserAccount(UserDto accountDto, String authority) {
        authorityRepository.save(Authority.builder()
                .authority(authority)
                .username(accountDto.getUsername())
                .build());

        userRepository.save(User.builder()
                .nameEn(nameTransliteration.transliterate(Constants.EN_LOCALE, accountDto.getNameEn()))
                .nameUa(nameTransliteration.transliterate(Constants.UA_LOCALE, accountDto.getNameEn()))
                .username(accountDto.getUsername())
                .password(passwordEncoder.encode(accountDto.getPassword()))
                .enabled(true)
                .build());

    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
