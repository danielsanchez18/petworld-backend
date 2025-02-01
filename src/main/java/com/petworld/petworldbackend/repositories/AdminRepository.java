package com.petworld.petworldbackend.repositories;

import com.petworld.petworldbackend.models.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    @Query("SELECT a FROM Admin a " +
            "JOIN User u ON a.user.idUser = u.idUser " +
            "WHERE LOWER(CONCAT(u.name, ' ', u.lastname)) " +
            "LIKE LOWER(CONCAT('%', :fullName, '%'))")
    Page<Admin> findByFullName(@Param("fullName") String fullName, Pageable pageable);

    Page<Admin> findByUser_EmailContainingIgnoreCase(String email, Pageable pageable);

    Page<Admin> findByUser_Enabled(boolean enabled, Pageable pageable);

}
