package com.petworld.petworldbackend.servicesImpl;

import com.petworld.petworldbackend.models.Product;
import com.petworld.petworldbackend.models.Sale;
import com.petworld.petworldbackend.models.SaleDetail;
import com.petworld.petworldbackend.repositories.SaleDetailRepository;
import com.petworld.petworldbackend.services.SaleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SaleDetailServiceImpl implements SaleDetailService {

    @Autowired
    private SaleDetailRepository saleDetailRepository;

    @Override
    public SaleDetail saveSaleDetail(SaleDetail saleDetail) {
        // Validar que el producto tenga suficiente stock
        Product product = saleDetail.getProduct();
        if (product.getStock() < saleDetail.getQuantity()) {
            throw new RuntimeException("No hay suficiente stock para el producto: " + product.getName());
        }

        // Calcular el subtotal
        saleDetail.setSubtotal(saleDetail.getQuantity() * saleDetail.getUnitPrice());

        // Guardar el detalle de venta
        SaleDetail savedSaleDetail = saleDetailRepository.save(saleDetail);

        // Actualizar el stock del producto
        product.setStock(product.getStock() - saleDetail.getQuantity());
        // Aquí deberías guardar el producto actualizado en la base de datos (necesitarías un ProductRepository).

        return savedSaleDetail;
    }

    @Override
    public Optional<SaleDetail> getSaleDetailById(UUID idSaleDetail) {
        return saleDetailRepository.findById(idSaleDetail);
    }

    @Override
    public List<SaleDetail> getAllSaleDetails() {
        return saleDetailRepository.findAll();
    }

    @Override
    public List<SaleDetail> getSaleDetailsBySale(Sale sale) {
        return saleDetailRepository.findBySale(sale);
    }

    @Override
    public List<SaleDetail> getSaleDetailsByProduct(Product product) {
        return saleDetailRepository.findByProduct(product);
    }

    @Override
    public List<SaleDetail> getSaleDetailsBySaleAndProduct(Sale sale, Product product) {
        return saleDetailRepository.findBySaleAndProduct(sale, product);
    }

    @Override
    public SaleDetail updateSaleDetail(UUID idSaleDetail, SaleDetail saleDetail) {
        SaleDetail existingSaleDetail = saleDetailRepository.findById(idSaleDetail)
                .orElseThrow(() -> new RuntimeException("Detalle de venta no encontrado"));

        // Actualizar campos permitidos
        existingSaleDetail.setQuantity(saleDetail.getQuantity());
        existingSaleDetail.setUnitPrice(saleDetail.getUnitPrice());
        existingSaleDetail.setSubtotal(saleDetail.getQuantity() * saleDetail.getUnitPrice());

        return saleDetailRepository.save(existingSaleDetail);
    }

    @Override
    public void deleteSaleDetail(UUID idSaleDetail) {
        if (!saleDetailRepository.existsById(idSaleDetail)) {
            throw new RuntimeException("Detalle de venta no encontrado");
        }
        saleDetailRepository.deleteById(idSaleDetail);
    }

}
