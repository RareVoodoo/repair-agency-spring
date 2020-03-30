package ua.testing.repairagency.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.testing.repairagency.dto.UserDTO;
import ua.testing.repairagency.entity.Authority;
import ua.testing.repairagency.entity.User;
import ua.testing.repairagency.region.Transliteration;
import ua.testing.repairagency.repository.AuthorityRepository;
import ua.testing.repairagency.repository.UserRepository;

import java.util.Locale;


@Service
@Transactional
public class UserService {
    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    private Transliteration transliteration = new Transliteration();


    public User registerNewUserAccount(UserDTO accountDto) {
        User user = new User();
        Authority authority = new Authority();

        if(LocaleContextHolder.getLocale().equals(Locale.US)){
            user.setNameEn((accountDto.getNameEn()));
            user.setNameUa(transliteration.transliterateToUa(accountDto.getNameEn()));
            logger.warn(accountDto.getUsername() + " latin -> Ukrainian");
        }else{
            user.setNameUa((accountDto.getNameEn()));
            user.setNameEn(transliteration.transliterateToEng(accountDto.getNameEn()));
            logger.warn(accountDto.getUsername() + " Ukrainian -> Latin");
        }
        user.setUsername(accountDto.getUsername());
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        user.setEnabled(true);

        authority.setAuthority("ROLE_USER");
        authority.setUsername(user.getUsername());

        authorityRepository.save(authority);
        return userRepository.save(user);
    }

    public User registerNewUserAccountWithCustomRole(UserDTO accountDto,String role) {
        User user = new User();
        Authority authority = new Authority();
        if(LocaleContextHolder.getLocale().equals(Locale.US)){
            user.setNameEn((accountDto.getNameEn()));
            user.setNameUa(transliteration.transliterateToUa(accountDto.getNameEn()));
            logger.warn(accountDto.getUsername() + " latin -> Ukrainian");
        }else{
            user.setNameUa((accountDto.getNameEn()));
            user.setNameEn(transliteration.transliterateToEng(accountDto.getNameEn()));
            logger.warn(accountDto.getUsername() + " Ukrainian -> Latin");
        }
        user.setUsername(accountDto.getUsername());
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        user.setEnabled(true);

        authority.setAuthority(role);
        authority.setUsername(user.getUsername());

        authorityRepository.save(authority);
        return userRepository.save(user);
    }
}
