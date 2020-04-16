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
                .username("user")
                .password("pass")
                .matchingPassword("pass")
                .build();

        UserDTO adminAccount1 = UserDTO.builder()
                .nameEn("Fedir Rybak")
                .username("manager")
                .password("pass")
                .matchingPassword("pass")
                .build();

        UserDTO masterAccount1 = UserDTO.builder()
                .nameEn("Serhiy Brylo")
                .username("master")
                .password("pass")
                .matchingPassword("pass")
                .build();



        userService.registerNewUserAccount(userAccount1);
        userService.registerNewUserAccount(adminAccount1,"ROLE_ADMIN");
        userService.registerNewUserAccount(masterAccount1, "ROLE_MASTER");

    }
}
