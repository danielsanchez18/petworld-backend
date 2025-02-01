package com.petworld.petworldbackend.repositories;

import com.petworld.petworldbackend.models.Owner;
import com.petworld.petworldbackend.models.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("SELECT p FROM Patient p " +
            "JOIN p.owner o " +
            "JOIN o.user u " +
            "WHERE LOWER(CONCAT(u.name, ' ', u.lastname)) " +
            "LIKE LOWER(CONCAT('%', :ownerFullName, '%'))")
    Page<Patient> findByOwnerFullName(String ownerFullName, Pageable pageable);

    Page<Patient> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Patient> findBySpecie(String specie, Pageable pageable);

    Page<Patient> findBySpecieAndName(String specie, String name, Pageable pageable);

    Page<Patient> findBySpecieAndBreed(String specie, String breed, Pageable pageable);

    Page<Patient> findBySpecieAndGender(String specie, Character gender, Pageable pageable);

    boolean existsByNameAndOwner(String name, Owner owner);

}
