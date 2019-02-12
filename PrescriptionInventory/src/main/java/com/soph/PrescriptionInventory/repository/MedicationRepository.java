package com.soph.PrescriptionInventory.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soph.PrescriptionInventory.model.ManufacturerModel;
import com.soph.PrescriptionInventory.model.MedicationModel;

@Repository
public interface MedicationRepository extends JpaRepository<MedicationModel,Long> {

//	List<MedicationModel> findByMedicationName(String medication_name);
	
}
