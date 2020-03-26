package ua.testing.repairagency.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.testing.repairagency.dto.UserDTO;
import ua.testing.repairagency.entity.User;
import ua.testing.repairagency.repository.UserRepository;
import ua.testing.repairagency.service.UserService;

@Component
public class UserValidator  implements Validator {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    private static final int DEFAULT_MIN_USERNAME_CHARS = 6;
    private static final int DEFAULT_MAX_USERNAME_CHARS = 32;
    private static final int DEFAULT_MIN_PASSWORD_LENGTH = 8;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
         UserDTO userDto = (UserDTO) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "field.required");
        if(userDto.getUsername().length() < DEFAULT_MIN_USERNAME_CHARS || userDto.getUsername().length() >DEFAULT_MAX_USERNAME_CHARS){
            errors.rejectValue("username", "size.userForm.username");
        }
        if(userRepository.findByUsername(userDto.getUsername()) !=null){
            errors.rejectValue("username", "duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password", "field.required");
        if (userDto.getPassword().length() < DEFAULT_MIN_PASSWORD_LENGTH){
            errors.rejectValue("password", "size.userForm.password");
        }

        if (!userDto.getMatchingPassword().equals(userDto.getPassword())) {
            errors.rejectValue("matchingPassword", "diff.userForm.passwordConfirm");
        }
    }
}
