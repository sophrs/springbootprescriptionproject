package com.soph.PrescriptionInventory.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import com.soph.PrescriptionInventory.model.ManufacturerModel;
import com.soph.PrescriptionInventory.model.MedicationModel;
import com.soph.PrescriptionInventory.repository.ManufacturerRepository;
import com.soph.PrescriptionInventory.repository.MedicationRepository;
@CrossOrigin
@RestController
@RequestMapping("/api")
public class ManufacturerController {

	@Autowired
	ManufacturerRepository myRepository;

	// method to create a manufacturer
	@PostMapping("/manufacturer")
	public ManufacturerModel createmanufacturer(@Valid @RequestBody ManufacturerModel mM) {
		return myRepository.save(mM);
	}

	// method to get all manufacturer
	@GetMapping("/manufacturer")
	public List<ManufacturerModel> getAllManufacturer() {
		return myRepository.findAll();
	}

	// method to get manufacturer by id
	@GetMapping("/manufacturer/{manufacturerid}")
	public ManufacturerModel getManufacturerbyID(@PathVariable(value = "medicationid") Long manID) {
		return myRepository.findById(manID)
				.orElseThrow(() -> new ResourceNotFoundException("ManufacturerModel", "manufacturerid", manID));
	}

	// method to update a manufacturer
	@PutMapping("/manufacturer/{manufacturerid}")
	public ManufacturerModel updateManufacturer(@PathVariable(value = "manufacturerid") Long manID, @Valid @RequestBody ManufacturerModel manDetails) {
		ManufacturerModel mM = myRepository.findById(manID).orElseThrow(()-> new ResourceNotFoundException("Manufacturer", "manufacturerid", manID));
		
			mM.setAddress(manDetails.getAddress());
			mM.setManufacturer_name(manDetails.getManufacturer_name());
			mM.setPhoneNumber(manDetails.getPhoneNumber());
			
			
			ManufacturerModel updateData = myRepository.save(mM);
			return updateData;
	}

	// method to remove a manufacturer by id
	@DeleteMapping("/manufacturer/{manufacturerid}")
	public ResponseEntity<?> deleteMedication(@PathVariable(value = "manufacturerid") Long manID) {
		ManufacturerModel mM = myRepository.findById(manID)
				.orElseThrow(() -> new ResourceNotFoundException("Manufacturer", "manufacturerid", manID));
		myRepository.delete(mM);
		return ResponseEntity.ok().build();
	}
	

}
