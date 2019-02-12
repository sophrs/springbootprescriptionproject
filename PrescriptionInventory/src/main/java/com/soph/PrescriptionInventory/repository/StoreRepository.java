package com.soph.PrescriptionInventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soph.PrescriptionInventory.model.StoreModel;
@Repository
public interface StoreRepository extends JpaRepository<StoreModel,Long>{

}
