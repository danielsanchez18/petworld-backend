package com.petworld.petworldbackend.servicesImpl;

import com.petworld.petworldbackend.exceptions.DuplicateResourceException;
import com.petworld.petworldbackend.models.Role;
import com.petworld.petworldbackend.models.User;
import com.petworld.petworldbackend.models.Veterinarian;
import com.petworld.petworldbackend.repositories.RoleRepository;
import com.petworld.petworldbackend.repositories.VeterinarianRepository;
import com.petworld.petworldbackend.services.UserService;
import com.petworld.petworldbackend.services.VeterinarianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeterinarianServiceImpl implements VeterinarianService {

    @Autowired
    private VeterinarianRepository veterinarianRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Veterinarian saveVeterinarian(Veterinarian veterinarian) throws DuplicateResourceException {

        Role veterinarianRole = roleRepository.findById(2L).orElseGet(() -> {
            Role newRole = new Role();
            newRole.setIdRole(2L);
            newRole.setName("veterinario");
            return roleRepository.save(newRole);
        });

        veterinarian.getUser().setRole(veterinarianRole);

        if (veterinarian.getUser().getIdUser() == null) {
            User savedUser = userService.saveUser(veterinarian.getUser());
            veterinarian.setUser(savedUser);
        }

        return veterinarianRepository.save(veterinarian);
    }

    @Override
    public Optional<Veterinarian> getVeterinarianById(Long idVeterinarian) {
        return veterinarianRepository.findById(idVeterinarian);
    }

    @Override
    public Page<Veterinarian> getVeterinariansBySpecialty(String specialty, Pageable pageable) {
        return veterinarianRepository.findBySpecialityContainingIgnoreCase(specialty, pageable);
    }

    @Override
    public List<Veterinarian> getAllVeterinarians() {
        return veterinarianRepository.findAll();
    }

    @Override
    public Page<Veterinarian> getAllVeterinarians(Pageable pageable) {
        return veterinarianRepository.findAll(pageable);
    }

    @Override
    public Page<Veterinarian> getVeterinariansByFullName(String fullName, Pageable pageable) {
        return veterinarianRepository.findByFullName(fullName, pageable);
    }

    @Override
    public Page<Veterinarian> getVeterinariansByEmail(String email, Pageable pageable) {
        return veterinarianRepository.findByUser_EmailContainingIgnoreCase(email, pageable);
    }

    @Override
    public Page<Veterinarian> getVeterinariansByEnabled(boolean enabled, Pageable pageable) {
        return veterinarianRepository.findByUser_Enabled(enabled, pageable);
    }

    @Override
    public Long getTotalVeterinarians() {
        return veterinarianRepository.count();
    }

    @Override
    public Veterinarian updateVeterinarian(Long idVeterinarian, Veterinarian veterinarian) throws DuplicateResourceException {

        Veterinarian exitingVeterinarian = veterinarianRepository.findById(idVeterinarian).orElseThrow(
                () -> new RuntimeException("Veterinario no encontrado")
        );

        User updatedVeterinarian = userService.updateUser(exitingVeterinarian.getUser().getIdUser(), veterinarian.getUser());
        exitingVeterinarian.setUser(updatedVeterinarian);
        exitingVeterinarian.setSpeciality(veterinarian.getSpeciality());

        return veterinarianRepository.save(exitingVeterinarian);
    }

    @Override
    public void deleteVeterinarian(Long idVeterinarian) {
        veterinarianRepository.deleteById(idVeterinarian);
        userService.deleteUser(veterinarianRepository.findById(idVeterinarian).get().getUser().getIdUser());
    }
}
