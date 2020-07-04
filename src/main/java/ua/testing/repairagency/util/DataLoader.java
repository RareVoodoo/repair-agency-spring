package ua.testing.repairagency.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ua.testing.repairagency.dto.UserDto;
import ua.testing.repairagency.service.UserService;

@Component
public class DataLoader implements CommandLineRunner {


    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {

        UserDto userAccount1 = UserDto.builder()
                .nameEn("Ivan Varenychenko")
                .username("user")
                .password("pass")
                .matchingPassword("pass")
                .build();

        UserDto adminAccount1 = UserDto.builder()
                .nameEn("Fedir Rybak")
                .username("manager")
                .password("pass")
                .matchingPassword("pass")
                .build();

        UserDto masterAccount1 = UserDto.builder()
                .nameEn("Serhiy Brylo")
                .username("master")
                .password("pass")
                .matchingPassword("pass")
                .build();



        userService.registerNewUserAccount(userAccount1);
        userService.registerNewUserAccount(adminAccount1,Constants.ADMIN_ROLE);
        userService.registerNewUserAccount(masterAccount1, Constants.MASTER_ROLE);

    }
}
