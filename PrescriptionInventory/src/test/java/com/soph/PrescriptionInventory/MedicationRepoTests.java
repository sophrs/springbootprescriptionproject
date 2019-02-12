package com.soph.PrescriptionInventory;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.soph.PrescriptionInventory.model.MedicationModel;
import com.soph.PrescriptionInventory.repository.MedicationRepository;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {PrescriptionInventoryApplication.class})
//@DataJpaTest
//public class MedicationRepoTests {
//	
//	@Autowired
//	private TestEntityManager entityManager;
//	
//	@Autowired
//	private MedicationRepository myRepo; 
//	
//	@Test
//	public void retrieveByIdTest() {
//		MedicationModel model1 = new MedicationModel("paracetamol", "123456789", (long) 23);
//		entityManager.persist(model1);
//		entityManager.flush();
//		assertTrue(myRepo.findById(model1.getmedId()).isPresent());
//	}
//	
//	@Test
//	public void retrieveByMedName() {
//		MedicationModel model1 = new MedicationModel("paracetamol", "123456789", (long) 23);
//		entityManager.persist(model1);
//		entityManager.flush();
//		assertFalse(myRepo.findByMedicationName("paracetamol").isEmpty());
//			
//	}
//
//}
