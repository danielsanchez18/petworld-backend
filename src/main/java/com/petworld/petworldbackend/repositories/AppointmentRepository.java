package com.petworld.petworldbackend.repositories;

import com.petworld.petworldbackend.models.Appointment;
import com.petworld.petworldbackend.models.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.UUID;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {

    Page<Appointment> findByPatient(Patient patient, Pageable pageable);

    Page<Appointment> findByStatus(String status, Pageable pageable);

    Page<Appointment> findByDate(Date date, Pageable pageable);

    Page<Appointment> findByPatientAndStatus(Patient patient, String status, Pageable pageable);

    Page<Appointment> findByPatientAndDate(Patient patient, Date date, Pageable pageable);

    Page<Appointment> findByDateAndStatus(Date date, String status, Pageable pageable);

    Page<Appointment> findByPatientAndDateAndStatus(Patient patient, Date date, String status, Pageable pageable);

}