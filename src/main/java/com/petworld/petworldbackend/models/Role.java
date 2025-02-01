package com.petworld.petworldbackend.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Role {

    @Id
    @Column(name = "id_role")
    private Long idRole;

    @Column(length = 25, nullable = false)
    private String name;

}
