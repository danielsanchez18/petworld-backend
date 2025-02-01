package com.petworld.petworldbackend.controllers;

import com.petworld.petworldbackend.models.User;
import com.petworld.petworldbackend.services.UserService;
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
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        try {
            User createdUser = userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).
                    body(ResponseUtil.successResponse("Usuario creado exitosamente", createdUser));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(ResponseUtil.errorResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body(ResponseUtil.errorResponse("Error al crear el usuario"));
        }
    }

    @GetMapping("/id/{idUser}")
    public ResponseEntity<?> getUserByIdUser(@PathVariable UUID idUser) {
        try {
            Optional<User> user = userService.getUserById(idUser);
            return ResponseEntity.ok(ResponseUtil.successResponse("Usuario encontrado", user));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseUtil.errorResponse(ex.getMessage()));
        }
    }

    @GetMapping()
    public ResponseEntity<?> getAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            return ResponseEntity.ok(ResponseUtil.successResponse("Usuarios encontrados exitosamente", users));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(ResponseUtil.errorResponse("Error al buscar los usuarios"));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsersPage(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Page<User> users = userService.getAllUsers(pageable);
            return ResponseEntity.ok(ResponseUtil.successResponse("Usuarios encontrados exitosamente", users));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(ResponseUtil.errorResponse("Error al buscar los usuarios"));
        }
    }

    @GetMapping("/fullname/{fullName}")
    public ResponseEntity<?> getUsersByFullName(@PathVariable String fullName, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Page<User> users = userService.getUsersByFullName(fullName, pageable);
            return ResponseEntity.ok(ResponseUtil.successResponse("Usuarios encontrados exitosamente", users));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(ResponseUtil.errorResponse("Error al buscar los usuarios"));
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getUsersByEmail(@PathVariable String email, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Page<User> users = userService.getUsersByEmail(email, pageable);
            return ResponseEntity.ok(ResponseUtil.successResponse("Usuarios encontrados exitosamente", users));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(ResponseUtil.errorResponse("Error al buscar los usuarios"));
        }
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<?> getUsersByRole(@PathVariable String role, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Page<User> users = userService.getUsersByRole(role, pageable);
            return ResponseEntity.ok(ResponseUtil.successResponse("Usuarios encontrados exitosamente", users));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(ResponseUtil.errorResponse("Error al buscar los usuarios"));
        }
    }

    @GetMapping("/enabled/{enabled}")
    public ResponseEntity<?> getUsersByEnabled(@PathVariable boolean enabled, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Page<User> users = userService.getUsersByEnabled(enabled, pageable);
            return ResponseEntity.ok(ResponseUtil.successResponse("Usuarios encontrados exitosamente", users));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(ResponseUtil.errorResponse("Error al buscar los usuarios"));
        }
    }

    @GetMapping("/total")
    public ResponseEntity<?> getTotalUsers() {
        try {
            Long totalUsers = userService.getTotalUsers();
            return ResponseEntity.ok(ResponseUtil.successResponse("Total de usuarios", totalUsers));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(ResponseUtil.errorResponse("Error al obtener el total de usuarios"));
        }
    }

    @PutMapping("/update/{idUser}")
    public ResponseEntity<?> updateUser(@PathVariable UUID idUser, @RequestBody User user) {
        try {
            User updatedUser = userService.updateUser(idUser, user);
            return ResponseEntity.ok(ResponseUtil.successResponse("Usuario actualizado exitosamente", updatedUser));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(ResponseUtil.errorResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body(ResponseUtil.errorResponse("Error al actualizar el usuario"));
        }
    }

    @DeleteMapping("/delete/{idUser}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID idUser) {
        try {
            userService.deleteUser(idUser);
            return ResponseEntity.ok(ResponseUtil.successResponse("Usuario eliminado exitosamente", null));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(ResponseUtil.errorResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body(ResponseUtil.errorResponse("Error al eliminar el usuario"));
        }
    }

}
