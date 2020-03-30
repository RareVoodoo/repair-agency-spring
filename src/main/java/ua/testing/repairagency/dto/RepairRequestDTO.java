package ua.testing.repairagency.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepairRequestDTO {


    private Long id;

    private String username;

    private String description;

    private boolean accepted;

    private boolean performed;

    private String cancellationReason;

    private Double repairPrice;

    private String userComment;

}
