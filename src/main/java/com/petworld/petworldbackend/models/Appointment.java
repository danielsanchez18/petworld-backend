package com.petworld.petworldbackend.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_appointment")
    private UUID idAppointment;

    @ManyToOne
    @JoinColumn(name = "id_patient", referencedColumnName = "id_patient", nullable = false)
    private Patient patient;

    @Column(nullable = false)
    private Date date;

    @Column(length = 20, nullable = false)
    private String reason;

    @Column(length = 25, nullable = false)
    private String status;

}
