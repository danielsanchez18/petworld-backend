package com.petworld.petworldbackend.servicesImpl;

import com.petworld.petworldbackend.models.MedicalRecord;
import com.petworld.petworldbackend.models.Patient;
import com.petworld.petworldbackend.models.Veterinarian;
import com.petworld.petworldbackend.repositories.MedicalRecordRepository;
import com.petworld.petworldbackend.services.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Override
    public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord) {
        return medicalRecordRepository.save(medicalRecord);
    }

    @Override
    public Optional<MedicalRecord> getMedicalRecordById(UUID idMedicalRecord) {
        return medicalRecordRepository.findById(idMedicalRecord);
    }

    @Override
    public List<MedicalRecord> getAllMedicalRecords() {
        return medicalRecordRepository.findAll();
    }

    @Override
    public Page<MedicalRecord> getAllMedicalRecords(Pageable pageable) {
        return medicalRecordRepository.findAll(pageable);
    }

    @Override
    public Page<MedicalRecord> getMedicalRecordsByPatient(Patient patient, Pageable pageable) {
        return medicalRecordRepository.findByPatient(patient, pageable);
    }

    @Override
    public Page<MedicalRecord> getMedicalRecordsByVeterinarian(Veterinarian veterinarian, Pageable pageable) {
        return medicalRecordRepository.findByVeterinarian(veterinarian, pageable);
    }

    @Override
    public Page<MedicalRecord> getMedicalRecordsByDate(Date date, Pageable pageable) {
        return medicalRecordRepository.findByDate(date, pageable);
    }

    @Override
    public Page<MedicalRecord> getMedicalRecordsByPatientAndVeterinarian(Patient patient, Veterinarian veterinarian, Pageable pageable) {
        return medicalRecordRepository.findByPatientAndVeterinarian(patient, veterinarian, pageable);
    }

    @Override
    public Page<MedicalRecord> getMedicalRecordsByPatientAndDate(Patient patient, Date date, Pageable pageable) {
        return medicalRecordRepository.findByPatientAndDate(patient, date, pageable);
    }

    @Override
    public Page<MedicalRecord> getMedicalRecordsByVeterinarianAndDate(Veterinarian veterinarian, Date date, Pageable pageable) {
        return medicalRecordRepository.findByVeterinarianAndDate(veterinarian, date, pageable);
    }

    @Override
    public Page<MedicalRecord> getMedicalRecordsByPatientAndVeterinarianAndDate(Patient patient, Veterinarian veterinarian, Date date, Pageable pageable) {
        return medicalRecordRepository.findByPatientAndVeterinarianAndDate(patient, veterinarian, date, pageable);
    }

    @Override
    public MedicalRecord updateMedicalRecord(UUID idMedicalRecord, MedicalRecord medicalRecord) {
        MedicalRecord existingMedicalRecord = medicalRecordRepository.findById(idMedicalRecord)
                .orElseThrow(() -> new RuntimeException("Historial médico no encontrado"));

        // Actualizar campos permitidos
        existingMedicalRecord.setPatient(medicalRecord.getPatient());
        existingMedicalRecord.setVeterinarian(medicalRecord.getVeterinarian());
        existingMedicalRecord.setDate(medicalRecord.getDate());
        existingMedicalRecord.setDiagnosis(medicalRecord.getDiagnosis());
        existingMedicalRecord.setTreatment(medicalRecord.getTreatment());
        existingMedicalRecord.setObservations(medicalRecord.getObservations());

        return medicalRecordRepository.save(existingMedicalRecord);
    }

    @Override
    public void deleteMedicalRecord(UUID idMedicalRecord) {
        if (!medicalRecordRepository.existsById(idMedicalRecord)) {
            throw new RuntimeException("Historial médico no encontrado");
        }
        medicalRecordRepository.deleteById(idMedicalRecord);
    }
}
