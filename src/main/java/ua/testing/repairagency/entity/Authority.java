package ua.testing.repairagency.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "authorities")
public class Authority {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String username;


    private String authority;


//
//    @OneToOne(cascade = CascadeType.ALL, optional = false)
//    @JoinColumn(name ="username",referencedColumnName = "username")
//    private User user;

}
