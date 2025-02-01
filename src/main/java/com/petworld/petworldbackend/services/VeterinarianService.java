package com.petworld.petworldbackend.services;

import com.petworld.petworldbackend.exceptions.DuplicateResourceException;
import com.petworld.petworldbackend.models.Veterinarian;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface VeterinarianService {

    Veterinarian saveVeterinarian(Veterinarian veterinarian) throws DuplicateResourceException;

    Optional<Veterinarian> getVeterinarianById(Long idVeterinarian);

    Page<Veterinarian> getVeterinariansBySpecialty(String specialty, Pageable pageable);

    List<Veterinarian> getAllVeterinarians();

    Page<Veterinarian> getAllVeterinarians(Pageable pageable);

    Page<Veterinarian> getVeterinariansByFullName(String fullName, Pageable pageable);

    Page<Veterinarian> getVeterinariansByEmail(String email, Pageable pageable);

    Page<Veterinarian> getVeterinariansByEnabled(boolean enabled, Pageable pageable);

    Long getTotalVeterinarians();

    Veterinarian updateVeterinarian(Long idVeterinarian, Veterinarian veterinarian) throws DuplicateResourceException;

    void deleteVeterinarian(Long idVeterinarian);

}