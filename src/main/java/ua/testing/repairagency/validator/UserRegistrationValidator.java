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

import java.util.regex.Pattern;

@Component
public class UserRegistrationValidator implements Validator {

    private final UserRepository userRepository;


    private static final String USERNAME_VALIDATION_REGEX = "([A-Za-z@_-]\\d*){1,20}";
    private static final String PASSWORD_VALIDATION_REGEX = "^(?=.*[0-9]).{8,15}$";
    private static final String USERNAME_FIELD_NAME = "username";
    private static final String PASSWORD_FIELD_NAME = "password";
    private static final String MATCHING_PASSWORD_FIELD_NAME = "matchingPassword";

    public UserRegistrationValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
         UserDTO userDto = (UserDTO) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, USERNAME_FIELD_NAME, "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,PASSWORD_FIELD_NAME, "field.required");

        if(userRepository.findByUsername(userDto.getUsername()).isPresent()){
            errors.rejectValue(USERNAME_FIELD_NAME, "duplicate.userForm.username");
        }

        if(!Pattern.matches(USERNAME_VALIDATION_REGEX,userDto.getUsername())){
            errors.rejectValue(USERNAME_FIELD_NAME, "size.userForm.username");
        }

        if (!Pattern.matches(PASSWORD_VALIDATION_REGEX, userDto.getPassword())){
            errors.rejectValue(PASSWORD_FIELD_NAME, "size.userForm.password");
        }

        if (!userDto.getMatchingPassword().equals(userDto.getPassword())) {
            errors.rejectValue(MATCHING_PASSWORD_FIELD_NAME, "diff.userForm.passwordConfirm");
        }

    }



}
