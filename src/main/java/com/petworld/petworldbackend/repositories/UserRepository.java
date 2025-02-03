package com.petworld.petworldbackend.repositories;

import com.petworld.petworldbackend.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    Page<User> findByEmailContainingIgnoreCase(String email, Pageable pageable);

    Page<User> findByRole_Name(String roleName, Pageable pageable);

    Page<User> findByEnabled(boolean enabled, Pageable pageable);

    @Query("SELECT u FROM User u " +
            "WHERE LOWER(CONCAT(u.name, ' ', u.lastname)) " +
            "LIKE LOWER(CONCAT('%', :fullName, '%'))")
    Page<User> findByFullName(@Param("fullName") String fullName, Pageable pageable);

    Optional<User> findByEmail(String email);

}
