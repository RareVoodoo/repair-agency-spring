package ua.testing.repairagency.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    Long Id;

    String nameUa;

    String nameEn;

    @NotEmpty
    String username;

    @NotEmpty
    String password;
    String matchingPassword;


}
