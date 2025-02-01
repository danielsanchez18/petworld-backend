package com.petworld.petworldbackend.services;

import com.petworld.petworldbackend.models.Owner;
import com.petworld.petworldbackend.models.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SaleService {

    Sale saveSale(Sale sale);

    Optional<Sale> getSaleById(UUID idSale);

    List<Sale> getAllSales();

    Page<Sale> getAllSales(Pageable pageable);

    Page<Sale> getSalesByOwner(Owner owner, Pageable pageable);

    Page<Sale> getSalesByDate(Date date, Pageable pageable);

    Page<Sale> getSalesByOwnerAndDate(Owner owner, Date date, Pageable pageable);

    Page<Sale> getSalesByDateRange(Date startDate, Date endDate, Pageable pageable);

    Page<Sale> getSalesByOwnerAndDateRange(Owner owner, Date startDate, Date endDate, Pageable pageable);

    Sale updateSale(UUID idSale, Sale sale);

    void deleteSale(UUID idSale);

}
