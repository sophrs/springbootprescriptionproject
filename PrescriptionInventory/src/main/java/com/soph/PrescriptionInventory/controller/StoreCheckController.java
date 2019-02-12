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
import com.soph.PrescriptionInventory.model.StockCheckModel;
import com.soph.PrescriptionInventory.model.StoreModel;
import com.soph.PrescriptionInventory.repository.StoreCheckRepository;
@CrossOrigin
@RestController
@RequestMapping("/api")
public class StoreCheckController {

	@Autowired
	StoreCheckRepository myRepository;

	// method to create a stock
	@PostMapping("/storecheck")
	public StockCheckModel createStore(@Valid @RequestBody StockCheckModel sCM) {
		return myRepository.save(sCM);
	}

	//method to get all stock
	@GetMapping("/stockcheck")
	public List<StockCheckModel> getAllStockChecks() {
		return myRepository.findAll();
	}
	
	//method to update 
	@PutMapping("/stockcheck/{medicationid}")
	public StockCheckModel updateStore(@PathVariable(value = "medicationid") Long medID, @Valid @RequestBody StockCheckModel stockDetails) {
		StockCheckModel sCM = myRepository.findById(medID).orElseThrow(()-> new ResourceNotFoundException("StockCheck", "medicationid", medID));
		
		sCM.setStockno(stockDetails.getStockno());
	
			StockCheckModel updateData = myRepository.save(sCM);
			return updateData;
	}
}
