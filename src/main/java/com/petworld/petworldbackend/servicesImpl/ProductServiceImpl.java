package com.petworld.petworldbackend.servicesImpl;

import com.petworld.petworldbackend.models.Product;
import com.petworld.petworldbackend.repositories.ProductRepository;
import com.petworld.petworldbackend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> getProductById(UUID idProduct) {
        return productRepository.findById(idProduct);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> getProductsByName(String name, Pageable pageable) {
        return productRepository.findByNameContainingIgnoreCase(name, pageable);
    }

    @Override
    public Page<Product> getProductsByActive(boolean active, Pageable pageable) {
        return productRepository.findByActive(active, pageable);
    }

    @Override
    public Page<Product> getProductsByNameAndActive(String name, boolean active, Pageable pageable) {
        return productRepository.findByNameContainingIgnoreCaseAndActive(name, active, pageable);
    }

    @Override
    public Page<Product> getProductsByPriceRange(double minPrice, double maxPrice, Pageable pageable) {
        return productRepository.findByPriceBetween(minPrice, maxPrice, pageable);
    }

    @Override
    public Page<Product> getProductsByStock(int stock, Pageable pageable) {
        return productRepository.findByStockGreaterThan(stock, pageable);
    }

    @Override
    public Long totalProducts() {
        return productRepository.count();
    }

    @Override
    public Product updateProduct(UUID idProduct, Product product) {
        Product existingProduct = productRepository.findById(idProduct)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Actualizar campos permitidos
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setStock(product.getStock());
        existingProduct.setImage(product.getImage());
        existingProduct.setActive(product.isActive());

        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(UUID idProduct) {
        if (!productRepository.existsById(idProduct)) {
            throw new RuntimeException("Producto no encontrado");
        }
        productRepository.deleteById(idProduct);
    }
}