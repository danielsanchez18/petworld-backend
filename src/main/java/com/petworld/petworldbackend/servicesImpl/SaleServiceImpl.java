package com.petworld.petworldbackend.servicesImpl;

import com.petworld.petworldbackend.models.Owner;
import com.petworld.petworldbackend.models.Sale;
import com.petworld.petworldbackend.models.SaleDetail;
import com.petworld.petworldbackend.repositories.SaleRepository;
import com.petworld.petworldbackend.services.SaleDetailService;
import com.petworld.petworldbackend.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private SaleDetailService saleDetailService;

    @Override
    public Sale saveSale(Sale sale) {
        // Validar que la venta tenga al menos un detalle de venta
        if (sale.getSaleDetails() == null || sale.getSaleDetails().isEmpty()) {
            throw new RuntimeException("La venta debe tener al menos un detalle de venta");
        }

        // Calcular el total de la venta sumando los subtotales de los detalles de venta
        double total = sale.getSaleDetails().stream()
                .mapToDouble(SaleDetail::getSubtotal)
                .sum();
        sale.setTotal(total);

        // Guardar la venta
        Sale savedSale = saleRepository.save(sale);

        // Guardar los detalles de venta asociados
        for (SaleDetail saleDetail : sale.getSaleDetails()) {
            saleDetail.setSale(savedSale);
            saleDetailService.saveSaleDetail(saleDetail);
        }

        return savedSale;
    }

    @Override
    public Optional<Sale> getSaleById(UUID idSale) {
        return saleRepository.findById(idSale);
    }

    @Override
    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    @Override
    public Page<Sale> getAllSales(Pageable pageable) {
        return saleRepository.findAll(pageable);
    }

    @Override
    public Page<Sale> getSalesByOwner(Owner owner, Pageable pageable) {
        return saleRepository.findByOwner(owner, pageable);
    }

    @Override
    public Page<Sale> getSalesByDate(Date date, Pageable pageable) {
        return saleRepository.findByDate(date, pageable);
    }

    @Override
    public Page<Sale> getSalesByOwnerAndDate(Owner owner, Date date, Pageable pageable) {
        return saleRepository.findByOwnerAndDate(owner, date, pageable);
    }

    @Override
    public Page<Sale> getSalesByDateRange(Date startDate, Date endDate, Pageable pageable) {
        return saleRepository.findByDateBetween(startDate, endDate, pageable);
    }

    @Override
    public Page<Sale> getSalesByOwnerAndDateRange(Owner owner, Date startDate, Date endDate, Pageable pageable) {
        return saleRepository.findByOwnerAndDateBetween(owner, startDate, endDate, pageable);
    }

    @Override
    public Sale updateSale(UUID idSale, Sale sale) {
        Sale existingSale = saleRepository.findById(idSale)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        // Actualizar campos permitidos
        existingSale.setOwner(sale.getOwner());
        existingSale.setDate(sale.getDate());
        existingSale.setTotal(sale.getTotal());

        return saleRepository.save(existingSale);
    }

    @Override
    public void deleteSale(UUID idSale) {
        if (!saleRepository.existsById(idSale)) {
            throw new RuntimeException("Venta no encontrada");
        }
        saleRepository.deleteById(idSale);
    }
}