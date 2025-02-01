package com.petworld.petworldbackend.services;

import com.petworld.petworldbackend.exceptions.DuplicateResourceException;
import com.petworld.petworldbackend.models.Owner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface OwnerService {

    Owner saveOwner(Owner owner) throws DuplicateResourceException;

    Optional<Owner> getOwnerById(Long idOwner);

    List<Owner> getAllOwners();

    Page<Owner> getAllOwners(Pageable pageable);

    Page<Owner> getOwnersByFullName(String fullName, Pageable pageable);

    Page<Owner> getOwnersByEmail(String email, Pageable pageable);

    Page<Owner> getOwnersByEnabled(boolean enabled, Pageable pageable);

    Long getTotalOwners();

    Owner updateOwner(Long idOwner, Owner owner) throws DuplicateResourceException;

    void deleteOwner(Long idOwner);

}