package ua.testing.repairagency;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import ua.testing.repairagency.dto.UserDto;
import ua.testing.repairagency.repository.AuthorityRepository;
import ua.testing.repairagency.repository.RepairRequestRepository;
import ua.testing.repairagency.repository.UserRepository;
import ua.testing.repairagency.service.UserDetailsServiceImp;
import ua.testing.repairagency.service.UserService;

import javax.sql.DataSource;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@DataJpaTest
@ComponentScan(basePackages = "ua.testing.repairagency")
public class DataJpaTests {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private RepairRequestRepository repairRequestRepository;

    @Autowired
    DataSource dataSource;

    @Autowired
    UserService userService;


    @Test
    public void assertInjectedComponentsAreNotNull(){
        assertThat(dataSource).isNotNull();
        assertThat(userRepository).isNotNull();
        assertThat(authorityRepository).isNotNull();
        assertThat(repairRequestRepository).isNotNull();
    }

    @Test
    public void whenSavedThenFindByUsername(){

        UserDto userAccount = UserDto.builder()
                .nameEn("Дмитро Теліга")
                .username("dteliga287")
                .password("testPass123")
                .matchingPassword("testPass123")
                .build();

        userService.registerNewUserAccount(userAccount);
        assertThat(userRepository.findByUsername("dteliga287")).isNotNull();
    }
}
