package com.petworld.petworldbackend.repositories;

import com.petworld.petworldbackend.models.Provider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {

    Provider findByName(String name);

    Page<Provider> findByNameContainingIgnoreCase(String name, Pageable pageable);

}
