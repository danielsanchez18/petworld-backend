package com.petworld.petworldbackend.repositories;

import com.petworld.petworldbackend.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Product> findByActive(boolean active, Pageable pageable);

    Page<Product> findByNameContainingIgnoreCaseAndActive(String name, boolean active, Pageable pageable);

    Page<Product> findByPriceBetween(double minPrice, double maxPrice, Pageable pageable);

    Page<Product> findByStockGreaterThan(int stock, Pageable pageable);

}