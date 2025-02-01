package com.petworld.petworldbackend.controllers;

import com.petworld.petworldbackend.models.Owner;
import com.petworld.petworldbackend.models.Sale;
import com.petworld.petworldbackend.services.SaleService;
import com.petworld.petworldbackend.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @PostMapping("/save")
    public ResponseEntity<?> saveSale(@RequestBody Sale sale) {
        try {
            Sale createdSale = saleService.saveSale(sale);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ResponseUtil.successResponse("Venta creada exitosamente", createdSale));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseUtil.errorResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtil.errorResponse("Error al crear la venta"));
        }
    }

    @GetMapping("/id/{idSale}")
    public ResponseEntity<?> getSaleById(@PathVariable UUID idSale) {
        try {
            Optional<Sale> sale = saleService.getSaleById(idSale);
            return ResponseEntity.ok(ResponseUtil.successResponse("Venta encontrada", sale));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtil.errorResponse(ex.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllSales() {
        try {
            List<Sale> sales = saleService.getAllSales();
            return ResponseEntity.ok(ResponseUtil.successResponse("Ventas encontradas exitosamente", sales));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtil.errorResponse("Error al buscar las ventas"));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllSales(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Page<Sale> sales = saleService.getAllSales(pageable);
            return ResponseEntity.ok(ResponseUtil.successResponse("Ventas encontradas exitosamente", sales));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtil.errorResponse("Error al buscar las ventas"));
        }
    }

    @GetMapping("/owner/{idOwner}")
    public ResponseEntity<?> getSalesByOwner(@PathVariable Long idOwner, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Owner owner = new Owner();
            owner.setIdOwner(idOwner);
            Page<Sale> sales = saleService.getSalesByOwner(owner, pageable);
            return ResponseEntity.ok(ResponseUtil.successResponse("Ventas encontradas exitosamente", sales));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtil.errorResponse("Error al buscar las ventas por cliente"));
        }
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<?> getSalesByDate(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
            @PageableDefault(size = 10, page = 0) Pageable pageable
    ) {
        try {
            Page<Sale> sales = saleService.getSalesByDate(date, pageable);
            return ResponseEntity.ok(ResponseUtil.successResponse("Ventas encontradas exitosamente", sales));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtil.errorResponse("Error al buscar las ventas por fecha"));
        }
    }

    @GetMapping("/owner/{idOwner}/date/{date}")
    public ResponseEntity<?> getSalesByOwnerAndDate(
            @PathVariable Long idOwner,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
            @PageableDefault(size = 10, page = 0) Pageable pageable
    ) {
        try {
            Owner owner = new Owner();
            owner.setIdOwner(idOwner);
            Page<Sale> sales = saleService.getSalesByOwnerAndDate(owner, date, pageable);
            return ResponseEntity.ok(ResponseUtil.successResponse("Ventas encontradas exitosamente", sales));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtil.errorResponse("Error al buscar las ventas por cliente y fecha"));
        }
    }

    @GetMapping("/date-range")
    public ResponseEntity<?> getSalesByDateRange(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @PageableDefault(size = 10, page = 0) Pageable pageable
    ){
        try {
            Page<Sale> sales = saleService.getSalesByDateRange(startDate, endDate, pageable);
            return ResponseEntity.ok(ResponseUtil.successResponse("Ventas encontradas exitosamente", sales));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtil.errorResponse("Error al buscar las ventas por rango de fechas"));
        }
    }

    @GetMapping("/owner/{idOwner}/date-range")
    public ResponseEntity<?> getSalesByOwnerAndDateRange(
            @PathVariable Long idOwner,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @PageableDefault(size = 10, page = 0) Pageable pageable
    ){
        try {
            Owner owner = new Owner();
            owner.setIdOwner(idOwner);
            Page<Sale> sales = saleService.getSalesByOwnerAndDateRange(owner, startDate, endDate, pageable);
            return ResponseEntity.ok(ResponseUtil.successResponse("Ventas encontradas exitosamente", sales));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtil.errorResponse("Error al buscar las ventas por cliente y rango de fechas"));
        }
    }

    @PutMapping("/update/{idSale}")
    public ResponseEntity<?> updateSale(@PathVariable UUID idSale, @RequestBody Sale sale) {
        try {
            Sale updatedSale = saleService.updateSale(idSale, sale);
            return ResponseEntity.ok(ResponseUtil.successResponse("Venta actualizada exitosamente", updatedSale));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseUtil.errorResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtil.errorResponse("Error al actualizar la venta"));
        }
    }

    @DeleteMapping("/delete/{idSale}")
    public ResponseEntity<?> deleteSale(@PathVariable UUID idSale) {
        try {
            saleService.deleteSale(idSale);
            return ResponseEntity.ok(ResponseUtil.successResponse("Venta eliminada exitosamente", null));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseUtil.errorResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtil.errorResponse("Error al eliminar la venta"));
        }
    }
}
