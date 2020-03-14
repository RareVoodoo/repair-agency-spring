package ua.testing.repairagency.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepairRequestDTO {


    private Long id;

    private String username;

    private String description;

    private boolean accepted;

    private boolean performed;
}
