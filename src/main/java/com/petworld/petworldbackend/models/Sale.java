package com.petworld.petworldbackend.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_sale")
    private UUID idSale;

    @ManyToOne
    @JoinColumn(name = "id_owner")
    private Owner owner;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private double total;

    // Relación uno a muchos con SaleDetail
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SaleDetail> saleDetails = new ArrayList<>();

    // Método para agregar un detalle de venta
    public void addSaleDetail(SaleDetail saleDetail) {
        saleDetails.add(saleDetail);
        saleDetail.setSale(this);
    }

    // Método para eliminar un detalle de venta
    public void removeSaleDetail(SaleDetail saleDetail) {
        saleDetails.remove(saleDetail);
        saleDetail.setSale(null);
    }

}