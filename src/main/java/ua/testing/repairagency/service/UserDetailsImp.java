package ua.testing.repairagency.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.testing.repairagency.entity.Authority;
import ua.testing.repairagency.entity.User;
import ua.testing.repairagency.repository.AuthorityRepository;
import ua.testing.repairagency.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserDetailsImp implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);
        Authority authority = authorityRepository.getAuthorityByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("No user with login " + username);
        }

        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),
                enabled,
                accountNonExpired,credentialsNonExpired,accountNonLocked,getAuthorities(authority.getAuthority()));
    }

    private static List<GrantedAuthority> getAuthorities (String role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
}
}
