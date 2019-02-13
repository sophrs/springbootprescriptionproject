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
import com.soph.PrescriptionInventory.model.StockCheckModel;
import com.soph.PrescriptionInventory.repository.MedicationRepository;
import com.soph.PrescriptionInventory.repository.StoreCheckRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PrescriptionInventoryApplication.class})
@AutoConfigureMockMvc
public class StoreCheckIntegrationTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private StoreCheckRepository myRepo;

	@Before
	public void clearDB() {
		myRepo.deleteAll();
	}
	
	@Test
	public void findandretreivestockcheckfromdb() throws Exception{
		myRepo.save(new StockCheckModel((long) 23, 123, 23));
		mvc.perform(get("/api/storecheck")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status()
						.isOk())
				.andExpect(content()
				.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0]storeid", is(23)));
	}
	
	@Test
	public void addStockCheckToDatabaseTest() throws Exception{
			mvc.perform(MockMvcRequestBuilders.post("/api/storecheck")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"storeid\" : 12 , \"stockno\" : 234, \"minimumstockno\": 23}"))
			.andExpect(status()
					.isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.stockno", is(234)));
	}
	
	@Test
	public void updateMedicationonDatabaseTest() throws Exception{
		StockCheckModel model1 = new StockCheckModel((long) 23, 123, 23);
		myRepo.save(model1);
		mvc.perform(put("/api/storecheck/" + model1.getMedicationid()).contentType(MediaType.APPLICATION_JSON)
				.content("{\"storeid\" : 12 , \"stockno\" : 234, \"minimumstockno\": 23}"))
		.andExpect(status()
				.isOk())
		.andExpect(content()
				.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.stockno", is(234)));
	}

}
