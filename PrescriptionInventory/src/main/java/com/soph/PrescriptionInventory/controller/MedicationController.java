package com.soph.PrescriptionInventory.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soph.PrescriptionInventory.exceptions.ResourceNotFoundException;
import com.soph.PrescriptionInventory.model.MedicationModel;
import com.soph.PrescriptionInventory.repository.MedicationRepository;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class MedicationController {

	@Autowired
	MedicationRepository myRepository;

	// method to create a medication
	@PostMapping("/medication")
	public MedicationModel createMedication(@Valid @RequestBody MedicationModel mM) {
		return myRepository.save(mM);
	}
	
	@GetMapping("/medication")
	public List<MedicationModel> getAllMedications() {
		return myRepository.findAll();
	}

	// method to get medication by id
	@GetMapping("/medication/{medicationid}")
	public MedicationModel getMedicationbyID(@PathVariable(value = "medicationid") Long medID) {
		return myRepository.findById(medID)
				.orElseThrow(() -> new ResourceNotFoundException("MedicationModel", "medicationid", medID));
	}

	// method to update a medication
	@PutMapping("/medication/{medicationid}")
	public MedicationModel updateMedication(@PathVariable(value = "medicationid") Long medID, @Valid @RequestBody MedicationModel medDetails) {
		MedicationModel mM = myRepository.findById(medID).orElseThrow(()-> new ResourceNotFoundException("Medication", "medicationid", medID));
		
			mM.setMedicationName(medDetails.getMedicationName());
			mM.setNHSNumber(medDetails.getNHSNumber());
			
			MedicationModel updateData = myRepository.save(mM);
			return updateData;
	}

	// method to remove a medication by id
	@DeleteMapping("/medication/{medicationid}")
	public ResponseEntity<?> deleteMedication(@PathVariable(value = "medicationid") Long medID) {
		MedicationModel mM = myRepository.findById(medID)
				.orElseThrow(() -> new ResourceNotFoundException("Medication", "medicationid", medID));
		myRepository.delete(mM);
		return ResponseEntity.ok().build();
	}
	
	
}
