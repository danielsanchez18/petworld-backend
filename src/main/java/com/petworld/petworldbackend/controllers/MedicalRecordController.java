package com.petworld.petworldbackend.controllers;

import com.petworld.petworldbackend.models.MedicalRecord;
import com.petworld.petworldbackend.models.Patient;
import com.petworld.petworldbackend.models.Veterinarian;
import com.petworld.petworldbackend.services.MedicalRecordService;
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
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/medical-record")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    // Guardar un historial médico
    @PostMapping("/save")
    public ResponseEntity<?> saveMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        try {
            MedicalRecord createdMedicalRecord = medicalRecordService.saveMedicalRecord(medicalRecord);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ResponseUtil.successResponse("Historial médico creado exitosamente", createdMedicalRecord));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseUtil.errorResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtil.errorResponse("Error al crear el historial médico"));
        }
    }

    // Obtener un historial médico por ID
    @GetMapping("/id/{idMedicalRecord}")
    public ResponseEntity<?> getMedicalRecordById(@PathVariable UUID idMedicalRecord) {
        try {
            Optional<MedicalRecord> medicalRecord = medicalRecordService.getMedicalRecordById(idMedicalRecord);
            return ResponseEntity.ok(ResponseUtil.successResponse("Historial médico encontrado", medicalRecord));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtil.errorResponse(ex.getMessage()));
        }
    }

    // Obtener todos los historiales médicos (sin paginación)
    @GetMapping
    public ResponseEntity<?> getAllMedicalRecords() {
        try {
            List<MedicalRecord> medicalRecords = medicalRecordService.getAllMedicalRecords();
            return ResponseEntity.ok(ResponseUtil.successResponse("Historiales médicos encontrados exitosamente", medicalRecords));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtil.errorResponse("Error al buscar los historiales médicos"));
        }
    }

    // Obtener todos los historiales médicos (con paginación)
    @GetMapping("/all")
    public ResponseEntity<?> getAllMedicalRecordsPage(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Page<MedicalRecord> medicalRecords = medicalRecordService.getAllMedicalRecords(pageable);
            return ResponseEntity.ok(ResponseUtil.successResponse("Historiales médicos encontrados exitosamente", medicalRecords));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtil.errorResponse("Error al buscar los historiales médicos"));
        }
    }

    // Obtener historiales médicos por paciente (con paginación)
    @GetMapping("/patient/{idPatient}")
    public ResponseEntity<?> getMedicalRecordsByPatient(@PathVariable Long idPatient, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Patient patient = new Patient();
            patient.setIdPatient(idPatient);
            Page<MedicalRecord> medicalRecords = medicalRecordService.getMedicalRecordsByPatient(patient, pageable);
            return ResponseEntity.ok(ResponseUtil.successResponse("Historiales médicos encontrados exitosamente", medicalRecords));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtil.errorResponse("Error al buscar los historiales médicos por paciente"));
        }
    }

    // Obtener historiales médicos por veterinario (con paginación)
    @GetMapping("/veterinarian/{idVeterinarian}")
    public ResponseEntity<?> getMedicalRecordsByVeterinarian(@PathVariable Long idVeterinarian, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Veterinarian veterinarian = new Veterinarian();
            veterinarian.setIdVeterinarian(idVeterinarian);
            Page<MedicalRecord> medicalRecords = medicalRecordService.getMedicalRecordsByVeterinarian(veterinarian, pageable);
            return ResponseEntity.ok(ResponseUtil.successResponse("Historiales médicos encontrados exitosamente", medicalRecords));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtil.errorResponse("Error al buscar los historiales médicos por veterinario"));
        }
    }

    // Obtener historiales médicos por fecha (con paginación)
    @GetMapping("/date/{date}")
    public ResponseEntity<?> getMedicalRecordsByDate(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
            @PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Page<MedicalRecord> medicalRecords = medicalRecordService.getMedicalRecordsByDate(date, pageable);
            return ResponseEntity.ok(ResponseUtil.successResponse("Historiales médicos encontrados exitosamente", medicalRecords));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtil.errorResponse("Error al buscar los historiales médicos por fecha"));
        }
    }

    // Obtener historiales médicos por paciente y veterinario (con paginación)
    @GetMapping("/patient/{idPatient}/veterinarian/{idVeterinarian}")
    public ResponseEntity<?> getMedicalRecordsByPatientAndVeterinarian(
            @PathVariable Long idPatient,
            @PathVariable Long idVeterinarian,
            @PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Patient patient = new Patient();
            patient.setIdPatient(idPatient);
            Veterinarian veterinarian = new Veterinarian();
            veterinarian.setIdVeterinarian(idVeterinarian);
            Page<MedicalRecord> medicalRecords = medicalRecordService.getMedicalRecordsByPatientAndVeterinarian(patient, veterinarian, pageable);
            return ResponseEntity.ok(ResponseUtil.successResponse("Historiales médicos encontrados exitosamente", medicalRecords));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtil.errorResponse("Error al buscar los historiales médicos por paciente y veterinario"));
        }
    }

    // Obtener historiales médicos por paciente y fecha (con paginación)
    @GetMapping("/patient/{idPatient}/date/{date}")
    public ResponseEntity<?> getMedicalRecordsByPatientAndDate(
            @PathVariable Long idPatient,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
            @PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Patient patient = new Patient();
            patient.setIdPatient(idPatient);
            Page<MedicalRecord> medicalRecords = medicalRecordService.getMedicalRecordsByPatientAndDate(patient, date, pageable);
            return ResponseEntity.ok(ResponseUtil.successResponse("Historiales médicos encontrados exitosamente", medicalRecords));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtil.errorResponse("Error al buscar los historiales médicos por paciente y fecha"));
        }
    }

    // Obtener historiales médicos por veterinario y fecha (con paginación)
    @GetMapping("/veterinarian/{idVeterinarian}/date/{date}")
    public ResponseEntity<?> getMedicalRecordsByVeterinarianAndDate(
            @PathVariable Long idVeterinarian,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
            @PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Veterinarian veterinarian = new Veterinarian();
            veterinarian.setIdVeterinarian(idVeterinarian);
            Page<MedicalRecord> medicalRecords = medicalRecordService.getMedicalRecordsByVeterinarianAndDate(veterinarian, date, pageable);
            return ResponseEntity.ok(ResponseUtil.successResponse("Historiales médicos encontrados exitosamente", medicalRecords));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtil.errorResponse("Error al buscar los historiales médicos por veterinario y fecha"));
        }
    }

    // Obtener historiales médicos por paciente, veterinario y fecha (con paginación)
    @GetMapping("/patient/{idPatient}/veterinarian/{idVeterinarian}/date/{date}")
    public ResponseEntity<?> getMedicalRecordsByPatientAndVeterinarianAndDate(
            @PathVariable Long idPatient,
            @PathVariable Long idVeterinarian,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
            @PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Patient patient = new Patient();
            patient.setIdPatient(idPatient);
            Veterinarian veterinarian = new Veterinarian();
            veterinarian.setIdVeterinarian(idVeterinarian);
            Page<MedicalRecord> medicalRecords = medicalRecordService.getMedicalRecordsByPatientAndVeterinarianAndDate(patient, veterinarian, date, pageable);
            return ResponseEntity.ok(ResponseUtil.successResponse("Historiales médicos encontrados exitosamente", medicalRecords));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtil.errorResponse("Error al buscar los historiales médicos por paciente, veterinario y fecha"));
        }
    }

    // Actualizar un historial médico
    @PutMapping("/update/{idMedicalRecord}")
    public ResponseEntity<?> updateMedicalRecord(@PathVariable UUID idMedicalRecord, @RequestBody MedicalRecord medicalRecord) {
        try {
            MedicalRecord updatedMedicalRecord = medicalRecordService.updateMedicalRecord(idMedicalRecord, medicalRecord);
            return ResponseEntity.ok(ResponseUtil.successResponse("Historial médico actualizado exitosamente", updatedMedicalRecord));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseUtil.errorResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtil.errorResponse("Error al actualizar el historial médico"));
        }
    }

    // Eliminar un historial médico
    @DeleteMapping("/delete/{idMedicalRecord}")
    public ResponseEntity<?> deleteMedicalRecord(@PathVariable UUID idMedicalRecord) {
        try {
            medicalRecordService.deleteMedicalRecord(idMedicalRecord);
            return ResponseEntity.ok(ResponseUtil.successResponse("Historial médico eliminado exitosamente", null));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseUtil.errorResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtil.errorResponse("Error al eliminar el historial médico"));
        }
    }

}