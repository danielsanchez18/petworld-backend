package com.petworld.petworldbackend.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "sale_detail")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SaleDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_sale_detail")
    private UUID idSaleDetail;

    @ManyToOne
    @JoinColumn(name = "id_sale")
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;

    @Column(nullable = false)
    private int quantity;

    @Column(name = "unit_price", nullable = false)
    private double unitPrice;

    @Column(nullable = false)
    private double subtotal;

}
