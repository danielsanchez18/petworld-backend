package com.petworld.petworldbackend.repositories;

import com.petworld.petworldbackend.models.Veterinarian;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VeterinarianRepository extends JpaRepository<Veterinarian, Long> {

    Page<Veterinarian> findBySpecialityContainingIgnoreCase(String specialty, Pageable pageable);

    @Query("SELECT v FROM Veterinarian v " +
            "JOIN User u ON v.user.idUser = u.idUser " +
            "WHERE LOWER(CONCAT(u.name, ' ', u.lastname)) " +
            "LIKE LOWER(CONCAT('%', :fullName, '%'))")
    Page<Veterinarian> findByFullName(@Param("fullName") String fullName, Pageable pageable);

    Page<Veterinarian> findByUser_EmailContainingIgnoreCase(String email, Pageable pageable);

    Page<Veterinarian> findByUser_Enabled(boolean enabled, Pageable pageable);


}
