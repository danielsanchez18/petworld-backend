package com.petworld.petworldbackend.repositories;

import com.petworld.petworldbackend.models.Owner;
import com.petworld.petworldbackend.models.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.UUID;

@Repository
public interface SaleRepository extends JpaRepository<Sale, UUID> {

    Page<Sale> findByOwner(Owner owner, Pageable pageable);

    Page<Sale> findByDate(Date date, Pageable pageable);

    Page<Sale> findByOwnerAndDate(Owner owner, Date date, Pageable pageable);

    Page<Sale> findByDateBetween(Date startDate, Date endDate, Pageable pageable);

    Page<Sale> findByOwnerAndDateBetween(Owner owner, Date startDate, Date endDate, Pageable pageable);

}
