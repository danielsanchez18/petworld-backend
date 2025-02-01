package com.petworld.petworldbackend.servicesImpl;

import com.petworld.petworldbackend.exceptions.DuplicateResourceException;
import com.petworld.petworldbackend.models.Owner;
import com.petworld.petworldbackend.models.Patient;
import com.petworld.petworldbackend.repositories.PatientRepository;
import com.petworld.petworldbackend.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    private void validatePatientUniqueness(String name, Owner owner) throws DuplicateResourceException {
        if (patientRepository.existsByNameAndOwner(name, owner)) {
            throw new DuplicateResourceException("Ya existe una mascota con el mismo nombre para este dueño");
        }
    }

    @Override
    public Patient savePatient(Patient patient) throws DuplicateResourceException {
        validatePatientUniqueness(patient.getName(), patient.getOwner());
        return patientRepository.save(patient);
    }

    @Override
    public Optional<Patient> getPatientById(Long idPatient) {
        return patientRepository.findById(idPatient);
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Page<Patient> getAllPatients(Pageable pageable) {
        return patientRepository.findAll(pageable);
    }

    @Override
    public Page<Patient> getPatientsByOwnerName(String ownerName, Pageable pageable) {
        return patientRepository.findByOwnerFullName(ownerName, pageable);
    }

    @Override
    public Page<Patient> getPatientsByName(String name, Pageable pageable) {
        return patientRepository.findByNameContainingIgnoreCase(name, pageable);
    }

    @Override
    public Page<Patient> getPatientsBySpecie(String specie, Pageable pageable) {
        return patientRepository.findBySpecie(specie, pageable);
    }

    @Override
    public Page<Patient> getPatientsBySpecieAndName(String specie, String name, Pageable pageable) {
        return patientRepository.findBySpecieAndName(specie, name, pageable);
    }

    @Override
    public Page<Patient> getPatientsBySpecieAndBreed(String specie, String breed, Pageable pageable) {
        return patientRepository.findBySpecieAndBreed(specie, breed, pageable);
    }

    @Override
    public Page<Patient> getPatientsBySpecieAndGender(String specie, Character gender, Pageable pageable) {
        return patientRepository.findBySpecieAndGender(specie, gender, pageable);
    }

    @Override
    public Long getTotalPatients() {
        return patientRepository.count();
    }

    @Override
    public Patient updatePatient(Long idPatient, Patient patient) throws DuplicateResourceException {
        Patient existingPatient = patientRepository.findById(idPatient)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));

        // Validar unicidad solo si el nombre o el dueño han cambiado
        if (!existingPatient.getName().equals(patient.getName()) ||
                !existingPatient.getOwner().equals(patient.getOwner())) {
            validatePatientUniqueness(patient.getName(), patient.getOwner());
        }

        // Actualizar campos permitidos
        existingPatient.setOwner(patient.getOwner());
        existingPatient.setName(patient.getName());
        existingPatient.setSpecie(patient.getSpecie());
        existingPatient.setBreed(patient.getBreed());
        existingPatient.setBirthDate(patient.getBirthDate());
        existingPatient.setGender(patient.getGender());
        existingPatient.setColor(patient.getColor());

        return patientRepository.save(existingPatient);
    }

    @Override
    public void deletePatient(Long idPatient) {
        patientRepository.deleteById(idPatient);
    }

}
