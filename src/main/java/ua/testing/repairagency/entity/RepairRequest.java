package ua.testing.repairagency.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "request")
public class RepairRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String description;

    @Column
    private boolean accepted;

    @Column
    private boolean performed;

    @Column
    private String cancellationReason;

    @Column
    private Long repairPriceUah;

    @Column
    private Long repairPriceUsd;

    @Column
    private String userComment;

    private String address;

    private String phoneNumber;
}
