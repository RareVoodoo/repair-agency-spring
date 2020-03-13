package ua.testing.repairagency.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {


    private Long Id;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;
    private String matchingPassword;


}
