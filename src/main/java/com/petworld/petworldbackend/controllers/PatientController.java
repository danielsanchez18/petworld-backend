package com.petworld.petworldbackend.controllers;

import com.petworld.petworldbackend.models.Patient;
import com.petworld.petworldbackend.services.PatientService;
import com.petworld.petworldbackend.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/save")
    public ResponseEntity<?> savePatient(@RequestBody Patient patient) {
        try {
            Patient createdPatient = patientService.savePatient(patient);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ResponseUtil.successResponse("Mascota creada exitosamente", createdPatient));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseUtil.errorResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtil.errorResponse("Error al crear la mascota"));
        }
    }

    @GetMapping("/id/{idPatient}")
    public ResponseEntity<?> getPatientById(@PathVariable Long idPatient) {
        try {
            Optional<Patient> patient = patientService.getPatientById(idPatient);
            return ResponseEntity.ok(ResponseUtil.successResponse("Mascota encontrada", patient));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtil.errorResponse(ex.getMessage()));
        }
    }

    @GetMapping()
    public ResponseEntity<?> getAllPatients() {
        try {
            return ResponseEntity.ok(ResponseUtil.successResponse("Mascotas encontradas exitosamente", patientService.getAllPatients()));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtil.errorResponse("Error al buscar las mascotas"));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllPatientsPage(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Page<Patient> patients = patientService.getAllPatients(pageable);
            return ResponseEntity.ok(ResponseUtil.successResponse("Mascotas encontradas exitosamente", patients));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtil.errorResponse("Error al buscar las mascotas"));
        }
    }

    @GetMapping("/owner/{ownerName}")
    public ResponseEntity<?> getPatientsByOwnerName(@PathVariable String ownerName, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Page<Patient> patients = patientService.getPatientsByOwnerName(ownerName, pageable);
            return ResponseEntity.ok(ResponseUtil.successResponse("Mascotas encontradas exitosamente", patients));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtil.errorResponse("Error al buscar las mascotas por nombre del dueño"));
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getPatientsByName(@PathVariable String name, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Page<Patient> patients = patientService.getPatientsByName(name, pageable);
            return ResponseEntity.ok(ResponseUtil.successResponse("Mascotas encontradas exitosamente", patients));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtil.errorResponse("Error al buscar las mascotas por nombre"));
        }
    }

    @GetMapping("/specie/{specie}")
    public ResponseEntity<?> getPatientsBySpecie(@PathVariable String specie, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Page<Patient> patients = patientService.getPatientsBySpecie(specie, pageable);
            return ResponseEntity.ok(ResponseUtil.successResponse("Mascotas encontradas exitosamente", patients));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtil.errorResponse("Error al buscar las mascotas por especie"));
        }
    }

    @GetMapping("/specie/{specie}/name/{name}")
    public ResponseEntity<?> getPatientsBySpecieAndName(@PathVariable String specie, @PathVariable String name, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Page<Patient> patients = patientService.getPatientsBySpecieAndName(specie, name, pageable);
            return ResponseEntity.ok(ResponseUtil.successResponse("Mascotas encontradas exitosamente", patients));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtil.errorResponse("Error al buscar las mascotas por especie y nombre"));
        }
    }

    @GetMapping("/specie/{specie}/breed/{breed}")
    public ResponseEntity<?> getPatientsBySpecieAndBreed(@PathVariable String specie, @PathVariable String breed, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Page<Patient> patients = patientService.getPatientsBySpecieAndBreed(specie, breed, pageable);
            return ResponseEntity.ok(ResponseUtil.successResponse("Mascotas encontradas exitosamente", patients));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtil.errorResponse("Error al buscar las mascotas por especie y raza"));
        }
    }

    @GetMapping("/specie/{specie}/gender/{gender}")
    public ResponseEntity<?> getPatientsBySpecieAndGender(@PathVariable String specie, @PathVariable Character gender, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Page<Patient> patients = patientService.getPatientsBySpecieAndGender(specie, gender, pageable);
            return ResponseEntity.ok(ResponseUtil.successResponse("Mascotas encontradas exitosamente", patients));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtil.errorResponse("Error al buscar las mascotas por especie y género"));
        }
    }

    @GetMapping("/total")
    public ResponseEntity<?> getTotalPatients() {
        try {
            Long totalPatients = patientService.getTotalPatients();
            return ResponseEntity.ok(ResponseUtil.successResponse("Total de mascotas", totalPatients));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtil.errorResponse("Error al obtener el total de mascotas"));
        }
    }

    @PutMapping("/update/{idPatient}")
    public ResponseEntity<?> updatePatient(@PathVariable Long idPatient, @RequestBody Patient patient) {
        try {
            Patient updatedPatient = patientService.updatePatient(idPatient, patient);
            return ResponseEntity.ok(ResponseUtil.successResponse("Mascota actualizada exitosamente", updatedPatient));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseUtil.errorResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtil.errorResponse("Error al actualizar la mascota"));
        }
    }

    @DeleteMapping("/delete/{idPatient}")
    public ResponseEntity<?> deletePatient(@PathVariable Long idPatient) {
        try {
            patientService.deletePatient(idPatient);
            return ResponseEntity.ok(ResponseUtil.successResponse("Mascota eliminada exitosamente", null));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseUtil.errorResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtil.errorResponse("Error al eliminar la mascota"));
        }
    }
}