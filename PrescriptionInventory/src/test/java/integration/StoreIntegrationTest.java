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
import com.soph.PrescriptionInventory.model.StoreModel;
import com.soph.PrescriptionInventory.repository.StoreRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PrescriptionInventoryApplication.class})
@AutoConfigureMockMvc
public class StoreIntegrationTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private StoreRepository myRepo;

	@Before
	public void clearDB() {
		myRepo.deleteAll();
	}
	
	@Test
	public void findandretreivestorefromdb() throws Exception{
		myRepo.save(new StoreModel("BootsChemist","12 Balamory Road", "12345678"));
		mvc.perform(get("/api/store")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content()
				.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0]storename", is("BootsChemist")));
	}
	
	@Test
	public void addStoretoDatabaseTest() throws Exception{
			mvc.perform(MockMvcRequestBuilders.post("/api/store")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"storename\" : \"BootsChemist\", \"address\" : \"12 Balamory Road\", \"phonenumber\": \"12345678}\"}"))
			.andExpect(status()
					.isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.storename", is("BootsChemist")));
	}
	
	@Test
	public void deleteStorefromDatabaseTest() throws Exception{
		StoreModel model1 = new StoreModel("BootsChemist","12 Balamory Road", "12345678");
		myRepo.save(model1);
		mvc.perform(delete("/api/store/"+ model1.getStoreid())).andExpect(status().isOk());
	}
	
	@Test
	public void updateStoreDatabaseTest() throws Exception{
		StoreModel model1 = new StoreModel("BootsChemist","12 Balamory Road", "12345678");
		myRepo.save(model1);
		mvc.perform(put("/api/store/" + model1.getStoreid()).contentType(MediaType.APPLICATION_JSON)
			.content("{\"storename\" : \"Superdrug\", \"address\" : \"12 Balamory Road\", \"phonenumber\": \"12345678}\"}"))
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.storename", is("Superdrug")));
		
		
	}
	

}
