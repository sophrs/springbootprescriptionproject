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
import com.soph.PrescriptionInventory.model.StoreModel;
import com.soph.PrescriptionInventory.repository.StoreRepository;
@CrossOrigin
@RestController
@RequestMapping("/api")
public class StoreController {
	@Autowired
	StoreRepository myRepository;

	// method to create a store
	@PostMapping("/store")
	public StoreModel createStore(@Valid @RequestBody StoreModel sM) {
		return myRepository.save(sM);
	}

	// method to get all store
	@GetMapping("/store")
	public List<StoreModel> getAllManufacturer() {
		return myRepository.findAll();
	}

	// method to get store by id
	@GetMapping("/store/{storeid}")
	public StoreModel getStorebyID(@PathVariable(value = "storeid") Long storeID) {
		return myRepository.findById(storeID)
				.orElseThrow(() -> new ResourceNotFoundException("StoreModel", "storeid", storeID));
	}

	// method to update a store
	@PutMapping("/store/{storeid}")
	public StoreModel updateStore(@PathVariable(value = "storeid") Long storeID, @Valid @RequestBody StoreModel storeDetails) {
		StoreModel sM = myRepository.findById(storeID).orElseThrow(()-> new ResourceNotFoundException("Store", "storeid", storeID));
		
		sM.setAddress(storeDetails.getAddress());
		sM.setPhonenumber(storeDetails.getPhonenumber());
		sM.setStorename(storeDetails.getStorename());
			
			
			StoreModel updateData = myRepository.save(sM);
			return updateData;
	}

	// method to remove a store by id
	@DeleteMapping("/store/{storeid}")
	public ResponseEntity<?> deleteMedication(@PathVariable(value = "storeid") Long storeID) {
		StoreModel sM = myRepository.findById(storeID)
				.orElseThrow(() -> new ResourceNotFoundException("Store", "storeid", storeID));
		myRepository.delete(sM);
		return ResponseEntity.ok().build();
	}


}
