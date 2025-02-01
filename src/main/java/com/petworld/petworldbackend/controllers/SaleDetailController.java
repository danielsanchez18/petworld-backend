package com.petworld.petworldbackend.controllers;

import com.petworld.petworldbackend.models.Product;
import com.petworld.petworldbackend.models.Sale;
import com.petworld.petworldbackend.models.SaleDetail;
import com.petworld.petworldbackend.services.SaleDetailService;
import com.petworld.petworldbackend.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/sale-detail")
public class SaleDetailController {

    @Autowired
    private SaleDetailService saleDetailService;

    @PostMapping("/save")
    public ResponseEntity<?> saveSaleDetail(@RequestBody SaleDetail saleDetail) {
        try {
            SaleDetail createdSaleDetail = saleDetailService.saveSaleDetail(saleDetail);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ResponseUtil.successResponse("Detalle de venta creado exitosamente", createdSaleDetail));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseUtil.errorResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtil.errorResponse("Error al crear el detalle de venta"));
        }
    }

    @GetMapping("/id/{idSaleDetail}")
    public ResponseEntity<?> getSaleDetailById(@PathVariable UUID idSaleDetail) {
        try {
            Optional<SaleDetail> saleDetail = saleDetailService.getSaleDetailById(idSaleDetail);
            return ResponseEntity.ok(ResponseUtil.successResponse("Detalle de venta encontrado", saleDetail));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtil.errorResponse(ex.getMessage()));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllSaleDetails() {
        try {
            List<SaleDetail> saleDetails = saleDetailService.getAllSaleDetails();
            return ResponseEntity.ok(ResponseUtil.successResponse("Detalles de venta encontrados exitosamente", saleDetails));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtil.errorResponse("Error al buscar los detalles de venta"));
        }
    }

    @GetMapping("/sale/{idSale}")
    public ResponseEntity<?> getSaleDetailsBySale(@PathVariable UUID idSale) {
        try {
            Sale sale = new Sale();
            sale.setIdSale(idSale);
            List<SaleDetail> saleDetails = saleDetailService.getSaleDetailsBySale(sale);
            return ResponseEntity.ok(ResponseUtil.successResponse("Detalles de venta encontrados exitosamente", saleDetails));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtil.errorResponse("Error al buscar los detalles de venta por venta"));
        }
    }

    @GetMapping("/product/{idProduct}")
    public ResponseEntity<?> getSaleDetailsByProduct(@PathVariable UUID idProduct) {
        try {
            Product product = new Product();
            product.setIdProduct(idProduct);
            List<SaleDetail> saleDetails = saleDetailService.getSaleDetailsByProduct(product);
            return ResponseEntity.ok(ResponseUtil.successResponse("Detalles de venta encontrados exitosamente", saleDetails));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtil.errorResponse("Error al buscar los detalles de venta por producto"));
        }
    }

    @PutMapping("/update/{idSaleDetail}")
    public ResponseEntity<?> updateSaleDetail(@PathVariable UUID idSaleDetail, @RequestBody SaleDetail saleDetail) {
        try {
            SaleDetail updatedSaleDetail = saleDetailService.updateSaleDetail(idSaleDetail, saleDetail);
            return ResponseEntity.ok(ResponseUtil.successResponse("Detalle de venta actualizado exitosamente", updatedSaleDetail));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseUtil.errorResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtil.errorResponse("Error al actualizar el detalle de venta"));
        }
    }

    @DeleteMapping("/delete/{idSaleDetail}")
    public ResponseEntity<?> deleteSaleDetail(@PathVariable UUID idSaleDetail) {
        try {
            saleDetailService.deleteSaleDetail(idSaleDetail);
            return ResponseEntity.ok(ResponseUtil.successResponse("Detalle de venta eliminado exitosamente", null));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseUtil.errorResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtil.errorResponse("Error al eliminar el detalle de venta"));
        }
    }
}