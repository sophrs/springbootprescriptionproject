package integration;


import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.soph.PrescriptionInventory.PrescriptionInventoryApplication;
import com.soph.PrescriptionInventory.model.MedicationModel;
import com.soph.PrescriptionInventory.repository.MedicationRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PrescriptionInventoryApplication.class})
@AutoConfigureMockMvc
public class MedicationIntegrationTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private MedicationRepository myRepo;

	@Before
	public void clearDB() {
		myRepo.deleteAll();
	}
	
	@Test
	public void findandretreivemedicationfromdb() throws Exception{
		myRepo.save(new MedicationModel("Paracetamol","123456789", 23));
		mvc.perform(get("/api/medication")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content()
				.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0]medicationName", is("Paracetamol")));
	}
	
	@Test
	public void addMedicationToDatabaseTest() throws Exception{
			mvc.perform(MockMvcRequestBuilders.post("/api/medication")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"medicationName\" : \"Paracetamol\", \"nhsnum\" : \"123456789\", \"manufacturerid\":23}"))
			.andExpect(status()
					.isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.medicationName", is("Paracetamol")));
	}
	
	@Test
	public void deleteMedicationfromDatabaseTest() throws Exception{
		MedicationModel model1 = new MedicationModel("Paracetamol","123456789", 23);
		myRepo.save(model1);
		mvc.perform(delete("/api/medication/"+ model1.getmedId())).andExpect(status().isOk());
	}
	
	@Test
	public void updateMedicationonDatabaseTest() throws Exception{
		MedicationModel model1 = new MedicationModel("Paracetamol","123456789", 23);
		myRepo.save(model1);
		mvc.perform(put("/api/medication/" + model1.getmedId()).contentType(MediaType.APPLICATION_JSON)
			.content("{\"medicationName\" : \"Adderoll\", \"nhsnum\" : \"123456789\", \"manufacturerid\":23}"))
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.medicationName", is("Adderoll")));
		
		
	}
	
	
}

