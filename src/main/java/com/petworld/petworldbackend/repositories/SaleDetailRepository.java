package com.petworld.petworldbackend.repositories;

import com.petworld.petworldbackend.models.Product;
import com.petworld.petworldbackend.models.Sale;
import com.petworld.petworldbackend.models.SaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SaleDetailRepository extends JpaRepository<SaleDetail, UUID> {

    // Buscar detalles de venta por venta (Sale)
    List<SaleDetail> findBySale(Sale sale);

    // Buscar detalles de venta por producto (Product)
    List<SaleDetail> findByProduct(Product product);

    // Buscar detalles de venta por venta y producto
    List<SaleDetail> findBySaleAndProduct(Sale sale, Product product);

}
