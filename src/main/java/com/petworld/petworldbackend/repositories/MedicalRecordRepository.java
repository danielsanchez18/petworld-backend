package com.petworld.petworldbackend.repositories;

import com.petworld.petworldbackend.models.MedicalRecord;
import com.petworld.petworldbackend.models.Patient;
import com.petworld.petworldbackend.models.Veterinarian;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.UUID;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, UUID> {

    Page<MedicalRecord> findByPatient(Patient patient, Pageable pageable);

    Page<MedicalRecord> findByVeterinarian(Veterinarian veterinarian, Pageable pageable);

    Page<MedicalRecord> findByDate(Date date, Pageable pageable);

    Page<MedicalRecord> findByPatientAndVeterinarian(Patient patient, Veterinarian veterinarian, Pageable pageable);

    Page<MedicalRecord> findByPatientAndDate(Patient patient, Date date, Pageable pageable);

    Page<MedicalRecord> findByVeterinarianAndDate(Veterinarian veterinarian, Date date, Pageable pageable);

    Page<MedicalRecord> findByPatientAndVeterinarianAndDate(Patient patient, Veterinarian veterinarian, Date date, Pageable pageable);

}