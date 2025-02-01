package com.petworld.petworldbackend.services;

import com.petworld.petworldbackend.exceptions.DuplicateResourceException;
import com.petworld.petworldbackend.models.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PatientService {

    Patient savePatient(Patient patient) throws DuplicateResourceException;

    Optional<Patient> getPatientById(Long idPatient);

    List<Patient> getAllPatients();

    Page<Patient> getAllPatients(Pageable pageable);

    Page<Patient> getPatientsByOwnerName(String ownerName, Pageable pageable);

    Page<Patient> getPatientsByName(String name, Pageable pageable);

    Page<Patient> getPatientsBySpecie(String specie, Pageable pageable);

    Page<Patient> getPatientsBySpecieAndName(String specie, String name, Pageable pageable);

    Page<Patient> getPatientsBySpecieAndBreed(String specie, String breed, Pageable pageable);

    Page<Patient> getPatientsBySpecieAndGender(String specie, Character gender, Pageable pageable);

    Long getTotalPatients();

    Patient updatePatient(Long idPatient, Patient patient) throws DuplicateResourceException;

    void deletePatient(Long idPatient);

}
