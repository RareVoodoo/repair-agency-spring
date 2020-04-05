package ua.testing.repairagency.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Transient;


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

    private Long repairPriceUah;

    private Long repairPriceUsd;

    private String userComment;


    private String address;


    private String phoneNumber;

}
