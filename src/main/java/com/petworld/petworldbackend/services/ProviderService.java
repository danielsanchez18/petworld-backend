package com.petworld.petworldbackend.services;

import com.petworld.petworldbackend.models.Provider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProviderService {

    Provider saveProvider(Provider provider);

    Optional<Provider> getProviderById(Long idProvider);

    List<Provider> getAllProviders();

    Page<Provider> getAllProviders(Pageable pageable);

    Page<Provider> getProvidersByName(String name, Pageable pageable);

    Long getTotalProviders();

    Provider updateProvider(Long idProvider, Provider provider);

    void deleteProvider(Long idProvider);

}
