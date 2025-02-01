package com.petworld.petworldbackend.servicesImpl;

import com.petworld.petworldbackend.exceptions.DuplicateResourceException;
import com.petworld.petworldbackend.models.Owner;
import com.petworld.petworldbackend.models.Role;
import com.petworld.petworldbackend.models.User;
import com.petworld.petworldbackend.repositories.OwnerRepository;
import com.petworld.petworldbackend.repositories.RoleRepository;
import com.petworld.petworldbackend.services.OwnerService;
import com.petworld.petworldbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Owner saveOwner(Owner owner) throws DuplicateResourceException {

        Role ownerRole = roleRepository.findById(3L).orElseGet(() -> {
            Role newRole = new Role();
            newRole.setIdRole(3L);
            newRole.setName("propietario");
            return roleRepository.save(newRole);
        });

        owner.getUser().setRole(ownerRole);

        if (owner.getUser().getIdUser() == null) {
            User savedUser = userService.saveUser(owner.getUser());
            owner.setUser(savedUser);
        }

        return ownerRepository.save(owner);
    }

    @Override
    public Optional<Owner> getOwnerById(Long idOwner) {
        return ownerRepository.findById(idOwner);
    }

    @Override
    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }

    @Override
    public Page<Owner> getAllOwners(Pageable pageable) {
        return ownerRepository.findAll(pageable);
    }

    @Override
    public Page<Owner> getOwnersByFullName(String fullName, Pageable pageable) {
        return ownerRepository.findByFullName(fullName, pageable);
    }

    @Override
    public Page<Owner> getOwnersByEmail(String email, Pageable pageable) {
        return ownerRepository.findByUser_EmailContainingIgnoreCase(email, pageable);
    }

    @Override
    public Page<Owner> getOwnersByEnabled(boolean enabled, Pageable pageable) {
        return ownerRepository.findByUser_Enabled(enabled, pageable);
    }

    @Override
    public Long getTotalOwners() {
        return ownerRepository.count();
    }

    @Override
    public Owner updateOwner(Long idOwner, Owner owner) throws DuplicateResourceException {

        Owner existingOwner = ownerRepository.findById(idOwner).orElseThrow(
                () -> new RuntimeException("Propietario no encontrado")
        );

        User updatedOwner = userService.updateUser(existingOwner.getUser().getIdUser(), owner.getUser());
        existingOwner.setUser(updatedOwner);

        return ownerRepository.save(existingOwner);
    }

    @Override
    public void deleteOwner(Long idOwner) {
        ownerRepository.deleteById(idOwner);
        userService.deleteUser(ownerRepository.findById(idOwner).get().getUser().getIdUser());
    }
}
