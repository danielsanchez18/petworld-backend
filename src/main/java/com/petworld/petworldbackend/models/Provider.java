package com.petworld.petworldbackend.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_provider")
    private Long idProvider;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 9, nullable = false)
    private String phone;

    @Column(length = 100)
    private String email;

    @Column(length = 200)
    private String address;

}
