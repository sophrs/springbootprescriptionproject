package integration;


import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.soph.PrescriptionInventory.model.MedicationModel;
import com.soph.PrescriptionInventory.repository.MedicationRepository;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {PrescriptionInventoryApplication.class})
//@AutoConfigureMockMvc
//public class MedicationIntegrationTest {
//	
//	@Autowired
//	private MockMvc mvc;
//	
//	@Autowired
//	private MedicationRepository myRepo;
//
//	@Before
//	public void clearDB() {
//		myRepo.deleteAll();
//	}
//	
//	@Test
//	public void findandretreivemedicationfromdb() throws Exception{
//		myRepo.save(new MedicationModel("Paracetamol","123456789",(long) 23));
//		mvc.perform(get("/api/medication")
//				.contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk())
//				.andExpect(content()
//				.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//				.andExpect(jsonPath("$[0]medication_name", is("Paracetamol")));
//	}
//	
//}
