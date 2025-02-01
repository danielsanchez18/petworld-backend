package com.petworld.petworldbackend.controllers;

import com.petworld.petworldbackend.models.Admin;
import com.petworld.petworldbackend.services.AdminService;
import com.petworld.petworldbackend.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/save")
    public ResponseEntity<?> saveAdmin(@RequestBody Admin admin) {
        try {
            Admin createdAdmin = adminService.saveAdmin(admin);
            return ResponseEntity.status(HttpStatus.CREATED).
                    body(ResponseUtil.successResponse("Administrador creado exitosamente", createdAdmin));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(ResponseUtil.errorResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body(ResponseUtil.errorResponse("Error al crear el administrador"));
        }
    }

    @GetMapping("/id/{idAdmin}")
    public ResponseEntity<?> getAdminByIdUser(@PathVariable Long idAdmin) {
        try {
            Optional<Admin> admin = adminService.getAdminById(idAdmin);
            return ResponseEntity.ok(ResponseUtil.successResponse("Administrador encontrado", admin));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseUtil.errorResponse(ex.getMessage()));
        }
    }

    @GetMapping()
    public ResponseEntity<?> getAllAdmins() {
        try {
            List<Admin> admins = adminService.getAllAdmins();
            return ResponseEntity.ok(ResponseUtil.successResponse("Administradores encontrados exitosamente", admins));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(ResponseUtil.errorResponse("Error al buscar los administradores"));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllAdminsPage(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Page<Admin> admins = adminService.getAllAdmins(pageable);
            return ResponseEntity.ok(ResponseUtil.successResponse("Administradores encontrados exitosamente", admins));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(ResponseUtil.errorResponse("Error al buscar los administradpres"));
        }
    }

    @GetMapping("/fullname/{fullName}")
    public ResponseEntity<?> getAdminsByFullName(@PathVariable String fullName, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Page<Admin> admins = adminService.getAdminsByFullName(fullName, pageable);
            return ResponseEntity.ok(ResponseUtil.successResponse("Administradores encontrados exitosamente", admins));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(ResponseUtil.errorResponse("Error al buscar los administradores"));
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getAdminsByEmail(@PathVariable String email, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Page<Admin> admins = adminService.getAdminsByEmail(email, pageable);
            return ResponseEntity.ok(ResponseUtil.successResponse("Administradores encontrados exitosamente", admins));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(ResponseUtil.errorResponse("Error al buscar los administradores"));
        }
    }

    @GetMapping("/enabled/{enabled}")
    public ResponseEntity<?> getAdminsByEnabled(@PathVariable boolean enabled, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Page<Admin> admins = adminService.getAdminsByEnabled(enabled, pageable);
            return ResponseEntity.ok(ResponseUtil.successResponse("Administradores encontrados exitosamente", admins));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(ResponseUtil.errorResponse("Error al buscar los administradores"));
        }
    }

    @GetMapping("/total")
    public ResponseEntity<?> getTotalAdmins() {
        try {
            Long totalAdmins = adminService.getTotalAdmins();
            return ResponseEntity.ok(ResponseUtil.successResponse("Total de administradores", totalAdmins));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(ResponseUtil.errorResponse("Error al obtener el total de administradores"));
        }
    }

    @PutMapping("/update/{idAdmin}")
    public ResponseEntity<?> updateAdmin(@PathVariable Long idAdmin, @RequestBody Admin admin) {
        try {
            Admin updatedAdmin = adminService.updateAdmin(idAdmin, admin);
            return ResponseEntity.ok(ResponseUtil.successResponse("Administrador actualizado exitosamente", updatedAdmin));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(ResponseUtil.errorResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body(ResponseUtil.errorResponse("Error al actualizar el administrador"));
        }
    }

    @DeleteMapping("/delete/{idAdmin}")
    public ResponseEntity<?> deleteAdmin(@PathVariable Long idAdmin) {
        try {
            adminService.deleteAdmin(idAdmin);
            return ResponseEntity.ok(ResponseUtil.successResponse("Administrador eliminado exitosamente", null));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(ResponseUtil.errorResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body(ResponseUtil.errorResponse("Error al eliminar el administrador"));
        }
    }

}
