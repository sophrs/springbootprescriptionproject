package com.soph.PrescriptionInventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soph.PrescriptionInventory.model.ManufacturerModel;

@Repository
public interface ManufacturerRepository extends JpaRepository<ManufacturerModel,Long> {

}
