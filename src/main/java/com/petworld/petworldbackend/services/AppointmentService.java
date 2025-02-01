package com.petworld.petworldbackend.services;

import com.petworld.petworldbackend.models.Appointment;
import com.petworld.petworldbackend.models.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AppointmentService {

    Appointment saveAppointment(Appointment appointment);

    Optional<Appointment> getAppointmentById(UUID idAppointment);

    List<Appointment> getAllAppointments();

    Page<Appointment> getAllAppointments(Pageable pageable);

    Page<Appointment> getAppointmentsByPatient(Patient patient, Pageable pageable);

    Page<Appointment> getAppointmentsByStatus(String status, Pageable pageable);

    Page<Appointment> getAppointmentsByDate(Date date, Pageable pageable);

    Page<Appointment> getAppointmentsByPatientAndStatus(Patient patient, String status, Pageable pageable);

    Page<Appointment> getAppointmentsByPatientAndDate(Patient patient, Date date, Pageable pageable);

    Page<Appointment> getAppointmentsByDateAndStatus(Date date, String status, Pageable pageable);

    Page<Appointment> getAppointmentsByPatientAndDateAndStatus(Patient patient, Date date, String status, Pageable pageable);

    Appointment updateAppointment(UUID idAppointment, Appointment appointment);

    void deleteAppointment(UUID idAppointment);

}