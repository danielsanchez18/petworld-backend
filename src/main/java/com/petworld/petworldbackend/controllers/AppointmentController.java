package com.petworld.petworldbackend.controllers;

import com.petworld.petworldbackend.models.Appointment;
import com.petworld.petworldbackend.models.Patient;
import com.petworld.petworldbackend.services.AppointmentService;
import com.petworld.petworldbackend.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/save")
    public ResponseEntity<?> saveAppointment(@RequestBody Appointment appointment) {
        try {
            Appointment createdAppointment = appointmentService.saveAppointment(appointment);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ResponseUtil.successResponse("Cita creada exitosamente", createdAppointment));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseUtil.errorResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtil.errorResponse("Error al crear la cita"));
        }
    }

    @GetMapping("/id/{idAppointment}")
    public ResponseEntity<?> getAppointmentById(@PathVariable UUID idAppointment) {
        try {
            Optional<Appointment> appointment = appointmentService.getAppointmentById(idAppointment);
            return ResponseEntity.ok(ResponseUtil.successResponse("Cita encontrada", appointment));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtil.errorResponse(ex.getMessage()));
        }
    }

    @GetMapping()
    public ResponseEntity<?> getAllAppointments() {
        try {
            return ResponseEntity.ok(ResponseUtil.successResponse("Citas encontradas exitosamente", appointmentService.getAllAppointments()));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtil.errorResponse("Error al buscar las citas"));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllAppointmentsPage(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Page<Appointment> appointments = appointmentService.getAllAppointments(pageable);
            return ResponseEntity.ok(ResponseUtil.successResponse("Citas encontradas exitosamente", appointments));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtil.errorResponse("Error al buscar las citas"));
        }
    }

    @GetMapping("/patient/{idPatient}")
    public ResponseEntity<?> getAppointmentsByPatient(@PathVariable Long idPatient, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Patient patient = new Patient();
            patient.setIdPatient(idPatient);
            Page<Appointment> appointments = appointmentService.getAppointmentsByPatient(patient, pageable);
            return ResponseEntity.ok(ResponseUtil.successResponse("Citas encontradas exitosamente", appointments));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtil.errorResponse("Error al buscar las citas por paciente"));
        }
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<?> getAppointmentsByStatus(@PathVariable String status, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Page<Appointment> appointments = appointmentService.getAppointmentsByStatus(status, pageable);
            return ResponseEntity.ok(ResponseUtil.successResponse("Citas encontradas exitosamente", appointments));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtil.errorResponse("Error al buscar las citas por estado"));
        }
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<?> getAppointmentsByDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Page<Appointment> appointments = appointmentService.getAppointmentsByDate(date, pageable);
            return ResponseEntity.ok(ResponseUtil.successResponse("Citas encontradas exitosamente", appointments));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtil.errorResponse("Error al buscar las citas por fecha"));
        }
    }

    @PutMapping("/update/{idAppointment}")
    public ResponseEntity<?> updateAppointment(@PathVariable UUID idAppointment, @RequestBody Appointment appointment) {
        try {
            Appointment updatedAppointment = appointmentService.updateAppointment(idAppointment, appointment);
            return ResponseEntity.ok(ResponseUtil.successResponse("Cita actualizada exitosamente", updatedAppointment));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseUtil.errorResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtil.errorResponse("Error al actualizar la cita"));
        }
    }

    @DeleteMapping("/delete/{idAppointment}")
    public ResponseEntity<?> deleteAppointment(@PathVariable UUID idAppointment) {
        try {
            appointmentService.deleteAppointment(idAppointment);
            return ResponseEntity.ok(ResponseUtil.successResponse("Cita eliminada exitosamente", null));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseUtil.errorResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtil.errorResponse("Error al eliminar la cita"));
        }
    }

}
