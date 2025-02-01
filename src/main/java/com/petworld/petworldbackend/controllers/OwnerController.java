package com.petworld.petworldbackend.controllers;

import com.petworld.petworldbackend.models.Admin;
import com.petworld.petworldbackend.models.Owner;
import com.petworld.petworldbackend.services.OwnerService;
import com.petworld.petworldbackend.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/owner")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @PostMapping("/save")
    public ResponseEntity<?> saveOwner(@RequestBody Owner owner) {
        try {
            Owner createdOwner = ownerService.saveOwner(owner);
            return ResponseEntity.status(HttpStatus.CREATED).
                    body(ResponseUtil.successResponse("Propietario creado exitosamente", createdOwner));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(ResponseUtil.errorResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body(ResponseUtil.errorResponse("Error al crear el propietario"));
        }
    }

    @GetMapping("/id/{idOwner}")
    public ResponseEntity<?> getOwnerByIdUser(@PathVariable Long idOwner) {
        try {
            Optional<Owner> owner = ownerService.getOwnerById(idOwner);
            return ResponseEntity.ok(ResponseUtil.successResponse("Propietario encontrado", owner));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseUtil.errorResponse(ex.getMessage()));
        }
    }

    @GetMapping()
    public ResponseEntity<?> getAllOwners() {
        try {
            return ResponseEntity.ok(ResponseUtil.successResponse("Propietarios encontrados exitosamente", ownerService.getAllOwners()));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(ResponseUtil.errorResponse("Error al buscar los propietarios"));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllOwnersPage(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Page<Owner> owners = ownerService.getAllOwners(pageable);
            return ResponseEntity.ok(ResponseUtil.successResponse("Propietarios encontrados exitosamente", owners));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(ResponseUtil.errorResponse("Error al buscar los propietarios"));
        }
    }

    @GetMapping("/fullname/{fullName}")
    public ResponseEntity<?> getOwnersByFullName(@PathVariable String fullName, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Page<Owner> owners = ownerService.getOwnersByFullName(fullName, pageable);
            return ResponseEntity.ok(ResponseUtil.successResponse("Propietarios encontrados exitosamente", owners));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(ResponseUtil.errorResponse("Error al buscar los propietarios"));
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getOwnersByEmail(@PathVariable String email, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Page<Owner> owners = ownerService.getOwnersByEmail(email, pageable);
            return ResponseEntity.ok(ResponseUtil.successResponse("Propietarios encontrados exitosamente", owners));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(ResponseUtil.errorResponse("Error al buscar los propietarios"));
        }
    }

    @GetMapping("/enabled/{enabled}")
    public ResponseEntity<?> getOwnersByEnabled(@PathVariable boolean enabled, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Page<Owner> owners = ownerService.getOwnersByEnabled(enabled, pageable);
            return ResponseEntity.ok(ResponseUtil.successResponse("Propietarios encontrados exitosamente", owners));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(ResponseUtil.errorResponse("Error al buscar los propietarios"));
        }
    }

    @GetMapping("/total")
    public ResponseEntity<?> getTotalOwners() {
        try {
            Long totalOwners = ownerService.getTotalOwners();
            return ResponseEntity.ok(ResponseUtil.successResponse("Total de propietarios", totalOwners));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(ResponseUtil.errorResponse("Error al obtener el total de propietarios"));
        }
    }

    @PutMapping("/update/{idOwner}")
    public ResponseEntity<?> updateOwner(@PathVariable Long idOwner, @RequestBody Owner owner) {
        try {
            Owner updatedOwner = ownerService.updateOwner(idOwner, owner);
            return ResponseEntity.ok(ResponseUtil.successResponse("Propietario actualizado exitosamente", updatedOwner));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(ResponseUtil.errorResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body(ResponseUtil.errorResponse("Error al actualizar el propietarios"));
        }
    }

    @DeleteMapping("/delete/{idOwner}")
    public ResponseEntity<?> deleteOwner(@PathVariable Long idOwner) {
        try {
            ownerService.deleteOwner(idOwner);
            return ResponseEntity.ok(ResponseUtil.successResponse("Propietario eliminado exitosamente", null));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(ResponseUtil.errorResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body(ResponseUtil.errorResponse("Error al eliminar el propietario"));
        }
    }

}
