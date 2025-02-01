package com.petworld.petworldbackend.services;

import com.petworld.petworldbackend.exceptions.DuplicateResourceException;
import com.petworld.petworldbackend.models.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AdminService {

    Admin saveAdmin(Admin Admin) throws DuplicateResourceException;

    Optional<Admin> getAdminById(Long idAdmin);

    List<Admin> getAllAdmins();

    Page<Admin> getAllAdmins(Pageable pageable);

    Page<Admin> getAdminsByFullName(String fullName, Pageable pageable);

    Page<Admin> getAdminsByEmail(String email, Pageable pageable);

    Page<Admin> getAdminsByEnabled(boolean enabled, Pageable pageable);

    Long getTotalAdmins();

    Admin updateAdmin(Long idAdmin, Admin Admin) throws DuplicateResourceException;

    void deleteAdmin(Long idAdmin);

}