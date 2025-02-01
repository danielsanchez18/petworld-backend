package com.petworld.petworldbackend.services;

import com.petworld.petworldbackend.models.Product;
import com.petworld.petworldbackend.models.Sale;
import com.petworld.petworldbackend.models.SaleDetail;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SaleDetailService {

    SaleDetail saveSaleDetail(SaleDetail saleDetail);

    Optional<SaleDetail> getSaleDetailById(UUID idSaleDetail);

    List<SaleDetail> getAllSaleDetails();

    List<SaleDetail> getSaleDetailsBySale(Sale sale);

    List<SaleDetail> getSaleDetailsByProduct(Product product);

    List<SaleDetail> getSaleDetailsBySaleAndProduct(Sale sale, Product product);

    SaleDetail updateSaleDetail(UUID idSaleDetail, SaleDetail saleDetail);

    void deleteSaleDetail(UUID idSaleDetail);

}
