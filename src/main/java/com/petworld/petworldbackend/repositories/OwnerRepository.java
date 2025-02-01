package com.petworld.petworldbackend.repositories;

import com.petworld.petworldbackend.models.Owner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {

    @Query("SELECT o FROM Owner o " +
            "JOIN User u ON o.user.idUser = u.idUser " +
            "WHERE LOWER(CONCAT(u.name, ' ', u.lastname)) " +
            "LIKE LOWER(CONCAT('%', :fullName, '%'))")
    Page<Owner> findByFullName(@Param("fullName") String fullName, Pageable pageable);

    Page<Owner> findByUser_EmailContainingIgnoreCase(String email, Pageable pageable);

    Page<Owner> findByUser_Enabled(boolean enabled, Pageable pageable);

}
