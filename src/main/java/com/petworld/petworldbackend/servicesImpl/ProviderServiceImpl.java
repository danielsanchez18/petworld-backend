package com.petworld.petworldbackend.servicesImpl;

import com.petworld.petworldbackend.models.Provider;
import com.petworld.petworldbackend.repositories.ProviderRepository;
import com.petworld.petworldbackend.services.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    private ProviderRepository providerRepository;

    // Validar que no se repitan los nombres de los proveedores
    private void validateProvider(String name) {
        Provider provider = providerRepository.findByName(name);
        if (provider != null) {
            throw new RuntimeException("Ya existe un proveedor con el nombre " + name);
        }
    }

    @Override
    public Provider saveProvider(Provider provider) {
        validateProvider(provider.getName());
        return providerRepository.save(provider);
    }

    @Override
    public Optional<Provider> getProviderById(Long idProvider) {
        return providerRepository.findById(idProvider);
    }

    @Override
    public List<Provider> getAllProviders() {
        return providerRepository.findAll();
    }

    @Override
    public Page<Provider> getAllProviders(Pageable pageable) {
        return providerRepository.findAll(pageable);
    }

    @Override
    public Page<Provider> getProvidersByName(String name, Pageable pageable) {
        return providerRepository.findByNameContainingIgnoreCase(name, pageable);
    }

    @Override
    public Long getTotalProviders() {
        return providerRepository.count();
    }

    @Override
    public Provider updateProvider(Long idProvider, Provider provider) {
        Provider existingProvider = providerRepository.findById(idProvider).orElseThrow(
                () -> new RuntimeException("No se encontró el proveedor.")
        );

        if (!existingProvider.getName().equals(provider.getName())) {
            validateProvider(provider.getName());
        }

        existingProvider.setName(provider.getName());

        return providerRepository.save(existingProvider);
    }

    @Override
    public void deleteProvider(Long idProvider) {
        providerRepository.deleteById(idProvider);
    }
}
