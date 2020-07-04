package ua.testing.repairagency.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.testing.repairagency.dto.UserDto;
import ua.testing.repairagency.entity.User;
import ua.testing.repairagency.repository.UserRepository;
import ua.testing.repairagency.util.Constants;

@Component
public class UserRegistrationValidator implements Validator {

    private final UserRepository userRepository;

    public UserRegistrationValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
         UserDto userDto = (UserDto) o;

        if (!userDto.getMatchingPassword().equals(userDto.getPassword())) {
            errors.rejectValue(Constants.MATCHING_PASSWORD_FIELD_NAME, Constants.MATCHING_PASSWORD_VALIDATION_PROPERTY);
        }

        if(userRepository.findByUsername(userDto.getUsername()).isPresent()){
            errors.rejectValue(Constants.USERNAME_FIELD_NAME, Constants.ALREADY_REGISTERED_USER_VALIDATION_PROPERTY);
        }

    }



}
