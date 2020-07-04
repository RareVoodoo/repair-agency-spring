package ua.testing.repairagency.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.testing.repairagency.util.Constants;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    Long Id;

    String nameUa;

    String nameEn;

    @NotEmpty
    @Pattern(regexp = Constants.USERNAME_VALIDATION_REGEX, message = Constants.USERNAME_VALIDATION_PROPERTY)
    String username;

    @NotEmpty
    @Pattern(regexp = Constants.PASSWORD_VALIDATION_REGEX, message = Constants.PASSWORD_VALIDATION_PROPERTY)
    String password;

    String matchingPassword;


}
