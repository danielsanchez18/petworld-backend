package com.petworld.petworldbackend.servicesImpl;

import com.petworld.petworldbackend.models.Appointment;
import com.petworld.petworldbackend.models.Patient;
import com.petworld.petworldbackend.repositories.AppointmentRepository;
import com.petworld.petworldbackend.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public Optional<Appointment> getAppointmentById(UUID idAppointment) {
        return appointmentRepository.findById(idAppointment);
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public Page<Appointment> getAllAppointments(Pageable pageable) {
        return appointmentRepository.findAll(pageable);
    }

    @Override
    public Page<Appointment> getAppointmentsByPatient(Patient patient, Pageable pageable) {
        return appointmentRepository.findByPatient(patient, pageable);
    }

    @Override
    public Page<Appointment> getAppointmentsByStatus(String status, Pageable pageable) {
        return appointmentRepository.findByStatus(status, pageable);
    }

    @Override
    public Page<Appointment> getAppointmentsByDate(Date date, Pageable pageable) {
        return appointmentRepository.findByDate(date, pageable);
    }

    @Override
    public Page<Appointment> getAppointmentsByPatientAndStatus(Patient patient, String status, Pageable pageable) {
        return appointmentRepository.findByPatientAndStatus(patient, status, pageable);
    }

    @Override
    public Page<Appointment> getAppointmentsByPatientAndDate(Patient patient, Date date, Pageable pageable) {
        return appointmentRepository.findByPatientAndDate(patient, date, pageable);
    }

    @Override
    public Page<Appointment> getAppointmentsByDateAndStatus(Date date, String status, Pageable pageable) {
        return appointmentRepository.findByDateAndStatus(date, status, pageable);
    }

    @Override
    public Page<Appointment> getAppointmentsByPatientAndDateAndStatus(Patient patient, Date date, String status, Pageable pageable) {
        return appointmentRepository.findByPatientAndDateAndStatus(patient, date, status, pageable);
    }

    @Override
    public Appointment updateAppointment(UUID idAppointment, Appointment appointment) {
        Appointment existingAppointment = appointmentRepository.findById(idAppointment)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));

        // Actualizar campos permitidos
        existingAppointment.setPatient(appointment.getPatient());
        existingAppointment.setDate(appointment.getDate());
        existingAppointment.setReason(appointment.getReason());
        existingAppointment.setStatus(appointment.getStatus());

        return appointmentRepository.save(existingAppointment);
    }

    @Override
    public void deleteAppointment(UUID idAppointment) {
        if (!appointmentRepository.existsById(idAppointment)) {
            throw new RuntimeException("Cita no encontrada");
        }
        appointmentRepository.deleteById(idAppointment);
    }
}
