package com.petworld.petworldbackend.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "medical_record")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_medical_record")
    private UUID idMedicalRecord;

    @ManyToOne
    @JoinColumn(name = "id_patient", referencedColumnName = "id_patient", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "id_veterinarian", referencedColumnName = "id_veterinarian", nullable = false)
    private Veterinarian veterinarian;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private String diagnosis;

    @Column(nullable = false)
    private String treatment;

    @Column(nullable = false)
    private String observations;

}
