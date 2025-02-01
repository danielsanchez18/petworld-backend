package com.petworld.petworldbackend.controllers;

import com.petworld.petworldbackend.models.Product;
import com.petworld.petworldbackend.services.ProductService;
import com.petworld.petworldbackend.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/save")
    public ResponseEntity<?> saveProduct(@RequestBody Product product) {
        try {
            Product createdProduct = productService.saveProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ResponseUtil.successResponse("Producto creado exitosamente", createdProduct));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseUtil.errorResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtil.errorResponse("Error al crear el producto"));
        }
    }

    @GetMapping("/id/{idProduct}")
    public ResponseEntity<?> getProductById(@PathVariable UUID idProduct) {
        try {
            Optional<Product> product = productService.getProductById(idProduct);
            return ResponseEntity.ok(ResponseUtil.successResponse("Producto encontrado", product));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtil.errorResponse(ex.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        try {
            List<Product> products = productService.getAllProducts();
            return ResponseEntity.ok(ResponseUtil.successResponse("Productos encontrados exitosamente", products));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtil.errorResponse("Error al buscar los productos"));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllProductsPage(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Page<Product> products = productService.getAllProducts(pageable);
            return ResponseEntity.ok(ResponseUtil.successResponse("Productos encontrados exitosamente", products));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtil.errorResponse("Error al buscar los productos"));
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getProductsByName(@PathVariable String name, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Page<Product> products = productService.getProductsByName(name, pageable);
            return ResponseEntity.ok(ResponseUtil.successResponse("Productos encontrados exitosamente", products));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtil.errorResponse("Error al buscar los productos por nombre"));
        }
    }

    @GetMapping("/active/{active}")
    public ResponseEntity<?> getProductsByActive(@PathVariable boolean active, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Page<Product> products = productService.getProductsByActive(active, pageable);
            return ResponseEntity.ok(ResponseUtil.successResponse("Productos encontrados exitosamente", products));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtil.errorResponse("Error al buscar los productos por estado"));
        }
    }

    @GetMapping("/name/{name}/active/{active}")
    public ResponseEntity<?> getProductsByNameAndActive(
            @PathVariable String name,
            @PathVariable boolean active,
            @PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Page<Product> products = productService.getProductsByNameAndActive(name, active, pageable);
            return ResponseEntity.ok(ResponseUtil.successResponse("Productos encontrados exitosamente", products));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtil.errorResponse("Error al buscar los productos por nombre y estado"));
        }
    }

    @GetMapping("/price-range")
    public ResponseEntity<?> getProductsByPriceRange(
            @RequestParam double minPrice,
            @RequestParam double maxPrice,
            @PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Page<Product> products = productService.getProductsByPriceRange(minPrice, maxPrice, pageable);
            return ResponseEntity.ok(ResponseUtil.successResponse("Productos encontrados exitosamente", products));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtil.errorResponse("Error al buscar los productos por rango de precios"));
        }
    }

    @GetMapping("/stock/{stock}")
    public ResponseEntity<?> getProductsByStock(@PathVariable int stock, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Page<Product> products = productService.getProductsByStock(stock, pageable);
            return ResponseEntity.ok(ResponseUtil.successResponse("Productos encontrados exitosamente", products));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtil.errorResponse("Error al buscar los productos por stock"));
        }
    }

    @GetMapping("/total")
    public ResponseEntity<?> totalProducts() {
        try {
            Long totalProducts = productService.totalProducts();
            return ResponseEntity.ok(ResponseUtil.successResponse("Total de productos encontrados", totalProducts));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtil.errorResponse("Error al buscar el total de productos"));
        }
    }

    @PutMapping("/update/{idProduct}")
    public ResponseEntity<?> updateProduct(@PathVariable UUID idProduct, @RequestBody Product product) {
        try {
            Product updatedProduct = productService.updateProduct(idProduct, product);
            return ResponseEntity.ok(ResponseUtil.successResponse("Producto actualizado exitosamente", updatedProduct));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseUtil.errorResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtil.errorResponse("Error al actualizar el producto"));
        }
    }

    @DeleteMapping("/delete/{idProduct}")
    public ResponseEntity<?> deleteProduct(@PathVariable UUID idProduct) {
        try {
            productService.deleteProduct(idProduct);
            return ResponseEntity.ok(ResponseUtil.successResponse("Producto eliminado exitosamente", null));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseUtil.errorResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtil.errorResponse("Error al eliminar el producto"));
        }
    }

}
