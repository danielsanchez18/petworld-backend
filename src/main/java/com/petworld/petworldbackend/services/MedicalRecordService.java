package com.petworld.petworldbackend.services;

import com.petworld.petworldbackend.models.MedicalRecord;
import com.petworld.petworldbackend.models.Patient;
import com.petworld.petworldbackend.models.Veterinarian;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MedicalRecordService {

    MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord);

    Optional<MedicalRecord> getMedicalRecordById(UUID idMedicalRecord);

    List<MedicalRecord> getAllMedicalRecords();

    Page<MedicalRecord> getAllMedicalRecords(Pageable pageable);

    Page<MedicalRecord> getMedicalRecordsByPatient(Patient patient, Pageable pageable);

    Page<MedicalRecord> getMedicalRecordsByVeterinarian(Veterinarian veterinarian, Pageable pageable);

    Page<MedicalRecord> getMedicalRecordsByDate(Date date, Pageable pageable);

    Page<MedicalRecord> getMedicalRecordsByPatientAndVeterinarian(Patient patient, Veterinarian veterinarian, Pageable pageable);

    Page<MedicalRecord> getMedicalRecordsByPatientAndDate(Patient patient, Date date, Pageable pageable);

    Page<MedicalRecord> getMedicalRecordsByVeterinarianAndDate(Veterinarian veterinarian, Date date, Pageable pageable);

    Page<MedicalRecord> getMedicalRecordsByPatientAndVeterinarianAndDate(Patient patient, Veterinarian veterinarian, Date date, Pageable pageable);

    MedicalRecord updateMedicalRecord(UUID idMedicalRecord, MedicalRecord medicalRecord);

    void deleteMedicalRecord(UUID idMedicalRecord);

}