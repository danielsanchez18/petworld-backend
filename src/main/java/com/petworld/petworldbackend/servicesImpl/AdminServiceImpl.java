package com.petworld.petworldbackend.servicesImpl;

import com.petworld.petworldbackend.exceptions.DuplicateResourceException;
import com.petworld.petworldbackend.models.Admin;
import com.petworld.petworldbackend.models.Role;
import com.petworld.petworldbackend.models.User;
import com.petworld.petworldbackend.repositories.AdminRepository;
import com.petworld.petworldbackend.repositories.RoleRepository;
import com.petworld.petworldbackend.services.AdminService;
import com.petworld.petworldbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    // Validar que exista por lo menos un administrador
    private void validateSingleAdmin() {
        if (adminRepository.count() == 1) {
            throw new RuntimeException("El sistema no puede quedar sin administradores.");
        }
    }

    @Override
    public Admin saveAdmin(Admin admin) throws DuplicateResourceException {

        Role adminRole = roleRepository.findById(1L).orElseGet(() -> {
            Role newRole = new Role();
            newRole.setIdRole(1L);
            newRole.setName("administrador");
            return roleRepository.save(newRole);
        });

        admin.getUser().setRole(adminRole);

        if (admin.getUser().getIdUser() == null) {
            User savedUser = userService.saveUser(admin.getUser());
            admin.setUser(savedUser);
        }

        return adminRepository.save(admin);
    }

    @Override
    public Optional<Admin> getAdminById(Long idAdmin) {
        return adminRepository.findById(idAdmin);
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public Page<Admin> getAllAdmins(Pageable pageable) {
        return adminRepository.findAll(pageable);
    }

    @Override
    public Page<Admin> getAdminsByFullName(String fullName, Pageable pageable) {
        return adminRepository.findByFullName(fullName, pageable);
    }

    @Override
    public Page<Admin> getAdminsByEmail(String email, Pageable pageable) {
        return adminRepository.findByUser_EmailContainingIgnoreCase(email, pageable);
    }

    @Override
    public Page<Admin> getAdminsByEnabled(boolean enabled, Pageable pageable) {
        return adminRepository.findByUser_Enabled(enabled, pageable);
    }

    @Override
    public Long getTotalAdmins() {
        return adminRepository.count();
    }

    @Override
    public Admin updateAdmin(Long idAdmin, Admin admin) throws DuplicateResourceException {
        Admin existingAdmin = adminRepository.findById(idAdmin).orElseThrow(
                () -> new RuntimeException("No se encontró el administrador.")
        );

        User updatedUser = userService.updateUser(existingAdmin.getUser().getIdUser(), admin.getUser());
        existingAdmin.setUser(updatedUser);

        return adminRepository.save(existingAdmin);
    }

    @Override
    public void deleteAdmin(Long idAdmin) {
        validateSingleAdmin();
        adminRepository.deleteById(idAdmin);
        userService.deleteUser(adminRepository.findById(idAdmin).get().getUser().getIdUser());
    }
}
