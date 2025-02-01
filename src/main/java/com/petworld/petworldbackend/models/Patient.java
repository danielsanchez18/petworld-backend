package com.petworld.petworldbackend.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_patient")
    private Long idPatient;

    @ManyToOne
    @JoinColumn(name = "id_owner", referencedColumnName = "id_owner", nullable = false)
    private Owner owner;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String specie;

    @Column(length = 50, nullable = false)
    private String breed;

    @Column(name = "birth_date", nullable = false)
    private Date birthDate;

    @Column(nullable = false)
    private Character gender;

    @Column(length = 50, nullable = false)
    private String color;

}
