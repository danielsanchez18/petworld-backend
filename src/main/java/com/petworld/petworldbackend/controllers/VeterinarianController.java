package com.petworld.petworldbackend.controllers;

import com.petworld.petworldbackend.exceptions.DuplicateResourceException;
import com.petworld.petworldbackend.models.Owner;
import com.petworld.petworldbackend.models.Veterinarian;
import com.petworld.petworldbackend.services.VeterinarianService;
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
@RequestMapping("/veterinarian")
public class VeterinarianController {

    @Autowired
    private VeterinarianService veterinarianService;

    @PostMapping("/save")
    public ResponseEntity<?> saveVeterinarian(@RequestBody Veterinarian veterinarian) {
        try {
            Veterinarian createdVeterinarian = veterinarianService.saveVeterinarian(veterinarian);
            return ResponseEntity.status(HttpStatus.CREATED).
                    body(ResponseUtil.successResponse("Veterinarian saved successfully", createdVeterinarian));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body(ResponseUtil.errorResponse("Failed to save veterinarian"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/id/{idVeterinarian}")
    public ResponseEntity<?> getVeterinarianById(@PathVariable Long idVeterinarian) {
        try {
            Optional<Veterinarian> veterinarian = veterinarianService.getVeterinarianById(idVeterinarian);
            return ResponseEntity.ok(ResponseUtil.successResponse("Veterinarian found", veterinarian));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseUtil.errorResponse(e.getMessage()));
        }
    }

    @GetMapping()
    public ResponseEntity<?> getAllVeterinarians() {
        try {
            List<Veterinarian> veterinarians = veterinarianService.getAllVeterinarians();
            return ResponseEntity.ok(ResponseUtil.successResponse("Veterinarians found successfully", veterinarians));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(ResponseUtil.errorResponse("Error finding veterinarians"));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllVeterinariansPage(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Page<Veterinarian> veterinarians = veterinarianService.getAllVeterinarians(pageable);
            return ResponseEntity.ok(ResponseUtil.successResponse("Veterinarians found successfully", veterinarians));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(ResponseUtil.errorResponse("Error finding veterinarians"));
        }
    }

    @GetMapping("/specialty/{specialty}")
    public ResponseEntity<?> getVeterinariansBySpecialty(@PathVariable String specialty, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Page<Veterinarian> veterinarians = veterinarianService.getVeterinariansBySpecialty(specialty, pageable);
            return ResponseEntity.ok(ResponseUtil.successResponse("Veterinarians encontrados exitosamente", veterinarians));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(ResponseUtil.errorResponse("Error al buscar los veterinarios"));
        }
    }

    @GetMapping("/fullname/{fullName}")
    public ResponseEntity<?> getVeterinariansByFullName(@PathVariable String fullName, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Page<Veterinarian> veterinarians = veterinarianService.getVeterinariansByFullName(fullName, pageable);
            return ResponseEntity.ok(ResponseUtil.successResponse("Veterinarios encontrados exitosamente", veterinarians));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(ResponseUtil.errorResponse("Error al buscar los veterinarios"));
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getVeterinariansByEmail(@PathVariable String email, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Page<Veterinarian> veterinarians = veterinarianService.getVeterinariansByEmail(email, pageable);
            return ResponseEntity.ok(ResponseUtil.successResponse("Veterinarians encontrados exitosamente", veterinarians));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(ResponseUtil.errorResponse("Error al buscar los veterinarios"));
        }
    }

    @GetMapping("/enabled/{enabled}")
    public ResponseEntity<?> getVeterinariansByEnabled(@PathVariable boolean enabled, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Page<Veterinarian> veterinarians = veterinarianService.getVeterinariansByEnabled(enabled, pageable);
            return ResponseEntity.ok(ResponseUtil.successResponse("Veterinarians encontrados exitosamente", veterinarians));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(ResponseUtil.errorResponse("Error al buscar los veterinarios"));
        }
    }

    @GetMapping("/total")
    public ResponseEntity<?> getTotalVeterinarians() {
        try {
            Long totalVeterinarians = veterinarianService.getTotalVeterinarians();
            return ResponseEntity.ok(ResponseUtil.successResponse("Total de veterinarios", totalVeterinarians));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(ResponseUtil.errorResponse("Error al obtener el total de veterinarios"));

        }
    }

    @PutMapping("/update/{idVeterinarian}")
    public ResponseEntity<?> updateVeterinarian(@PathVariable Long idVeterinarian, @RequestBody Veterinarian veterinarian) {
        try {
            Veterinarian updatedVeterinarian = veterinarianService.updateVeterinarian(idVeterinarian, veterinarian);
            return ResponseEntity.ok(ResponseUtil.successResponse("Veterinarian actualizado exitosamente", updatedVeterinarian));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(ResponseUtil.errorResponse(ex.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body(ResponseUtil.errorResponse("Error al actualizar el veterinario"));
        }
    }

    @DeleteMapping("/delete/{idVeterinarian}")
    public ResponseEntity<?> deleteVeterinarian(@PathVariable Long idVeterinarian) {
        try {
            veterinarianService.deleteVeterinarian(idVeterinarian);
            return ResponseEntity.ok(ResponseUtil.successResponse("Veterinario eliminado exitosamente", null));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(ResponseUtil.errorResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body(ResponseUtil.errorResponse("Error al eliminar el veterinario"));
        }
    }

}
