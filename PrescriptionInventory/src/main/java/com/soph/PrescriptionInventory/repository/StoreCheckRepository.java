package com.soph.PrescriptionInventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soph.PrescriptionInventory.model.StockCheckModel;

@Repository
public interface StoreCheckRepository extends JpaRepository<StockCheckModel,Long> {

}
