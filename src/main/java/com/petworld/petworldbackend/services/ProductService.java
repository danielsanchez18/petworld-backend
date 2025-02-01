package com.petworld.petworldbackend.services;

import com.petworld.petworldbackend.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {

    Product saveProduct(Product product);

    Optional<Product> getProductById(UUID idProduct);

    List<Product> getAllProducts();

    Page<Product> getAllProducts(Pageable pageable);

    Page<Product> getProductsByName(String name, Pageable pageable);

    Page<Product> getProductsByActive(boolean active, Pageable pageable);

    Page<Product> getProductsByNameAndActive(String name, boolean active, Pageable pageable);

    Page<Product> getProductsByPriceRange(double minPrice, double maxPrice, Pageable pageable);

    Page<Product> getProductsByStock(int stock, Pageable pageable);

    Long totalProducts();

    Product updateProduct(UUID idProduct, Product product);

    void deleteProduct(UUID idProduct);

}