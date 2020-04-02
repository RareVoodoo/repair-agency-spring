package ua.testing.repairagency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ua.testing.repairagency.dto.UserDTO;
import ua.testing.repairagency.service.UserService;

@Component
public class DataLoader implements CommandLineRunner {


    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {

        UserDTO userAccount1 = UserDTO.builder()
                .nameEn("Ivan Varenychenko")
                .username("user1")
                .password("pass")
                .matchingPassword("pass")
                .build();

        UserDTO adminAccount1 = UserDTO.builder()
                .nameEn("Fedir Rybak")
                .username("manager1")
                .password("pass")
                .matchingPassword("pass")
                .build();

        UserDTO masterAccount1 = UserDTO.builder()
                .nameEn("Serhiy Brylo")
                .username("master1")
                .password("pass")
                .matchingPassword("pass")
                .build();

        UserDTO masterAccount2 = UserDTO.builder()
                .nameEn("Vysko Yan")
                .username("master2")
                .password("pass")
                .matchingPassword("pass")
                .build();


        userService.registerNewUserAccount(userAccount1);
        userService.registerNewUserAccountWithCustomRole(adminAccount1,"ROLE_ADMIN");
        userService.registerNewUserAccountWithCustomRole(masterAccount1, "ROLE_MASTER");
        userService.registerNewUserAccountWithCustomRole(masterAccount2, "ROLE_MASTER");

    }
}
