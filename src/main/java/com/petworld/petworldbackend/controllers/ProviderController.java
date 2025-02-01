package com.petworld.petworldbackend.controllers;

import com.petworld.petworldbackend.models.Provider;
import com.petworld.petworldbackend.services.ProviderService;
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
@RequestMapping("/provider")
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @PostMapping("/save")
    public ResponseEntity<?> saveProvider(@RequestBody Provider provider) {
        try {
            Provider createdProvider = providerService.saveProvider(provider);
            return ResponseEntity.status(HttpStatus.CREATED).
                    body(ResponseUtil.successResponse("Proveedor creado exitosamente", createdProvider));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(ResponseUtil.errorResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body(ResponseUtil.errorResponse("Error al crear el proveedor"));
        }
    }

    @GetMapping("/id/{idProvider}")
    public ResponseEntity<?> getProviderByIdUser(@PathVariable Long idProvider) {
        try {
            Optional<Provider> provider = providerService.getProviderById(idProvider);
            return ResponseEntity.ok(ResponseUtil.successResponse("Proveedor encontrado", provider));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseUtil.errorResponse(ex.getMessage()));
        }
    }

    @GetMapping()
    public ResponseEntity<?> getAllProviders() {
        try {
            return ResponseEntity.ok(ResponseUtil.successResponse("Proveedores encontrados exitosamente", providerService.getAllProviders()));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(ResponseUtil.errorResponse("Error al buscar los proveedores"));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllProvidersPage(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Page<Provider> providers = providerService.getAllProviders(pageable);
            return ResponseEntity.ok(ResponseUtil.successResponse("Proveedores encontrados exitosamente", providers));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(ResponseUtil.errorResponse("Error al buscar los proveedores"));
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getProvidersByFullName(@PathVariable String name, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Page<Provider> providers = providerService.getProvidersByName(name, pageable);
            return ResponseEntity.ok(ResponseUtil.successResponse("Proveedores encontrados exitosamente", providers));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(ResponseUtil.errorResponse("Error al buscar los proveedores"));
        }
    }

    @GetMapping("/total")
    public ResponseEntity<?> getTotalProviders() {
        try {
            Long totalProviders = providerService.getTotalProviders();
            return ResponseEntity.ok(ResponseUtil.successResponse("Total de proveedores", totalProviders));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(ResponseUtil.errorResponse("Error al obtener el total de proveedores"));
        }
    }

    @PutMapping("/update/{idProvider}")
    public ResponseEntity<?> updateProvider(@PathVariable Long idProvider, @RequestBody Provider provider) {
        try {
            Provider updatedProvider = providerService.updateProvider(idProvider, provider);
            return ResponseEntity.ok(ResponseUtil.successResponse("Proveedor actualizado exitosamente", updatedProvider));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(ResponseUtil.errorResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body(ResponseUtil.errorResponse("Error al actualizar el proveedor"));
        }
    }

    @DeleteMapping("/delete/{idProvider}")
    public ResponseEntity<?> deleteProvider(@PathVariable Long idProvider) {
        try {
            providerService.deleteProvider(idProvider);
            return ResponseEntity.ok(ResponseUtil.successResponse("Proveedor eliminado exitosamente", null));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(ResponseUtil.errorResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body(ResponseUtil.errorResponse("Error al eliminar el proveedor"));
        }
    }

}
