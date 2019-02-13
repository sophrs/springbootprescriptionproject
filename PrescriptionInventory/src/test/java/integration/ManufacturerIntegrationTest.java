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
import com.soph.PrescriptionInventory.model.ManufacturerModel;
import com.soph.PrescriptionInventory.model.StoreModel;
import com.soph.PrescriptionInventory.repository.ManufacturerRepository;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PrescriptionInventoryApplication.class})
@AutoConfigureMockMvc
public class ManufacturerIntegrationTest {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ManufacturerRepository myRepo;

	@Before
	public void clearDB() {
		myRepo.deleteAll();
	}
	
	
	@Test
	public void findandretreivemanufacturerfromdb() throws Exception{
		myRepo.save(new ManufacturerModel ("BootsBrand","12345678", "12 Balamory Road"));
		mvc.perform(get("/api/manufacturer")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content()
				.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0]manufacturer_name", is("BootsBrand")));
	}
	
	@Test
	public void addManufacturertoDatabaseTest() throws Exception{
			mvc.perform(MockMvcRequestBuilders.post("/api/manufacturer")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"manufacturer_name\" : \"BootsChemist\", \"address\" : \"12 Balamory Road\", \"phonenumber\": \"12345678}\"}"))
			.andExpect(status()
					.isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.manufacturer_name", is("BootsChemist")));
	}
	
	@Test
	public void deleteManufacturerfromDatabaseTest() throws Exception{
		ManufacturerModel model1 = new ManufacturerModel("BootsChemist","12345678", "12 Balamory Road");
		myRepo.save(model1);
		mvc.perform(delete("/api/manufacturer/"+ model1.getmanId())).andExpect(status().isOk());
	}
	
	@Test
	public void updateManufacturerDatabaseTest() throws Exception{
		ManufacturerModel model1 = new ManufacturerModel("BootsChemist","12345675", "12 Balamory Road");
		myRepo.save(model1);
		mvc.perform(put("/api/manufacturer/" + model1.getmanId()).contentType(MediaType.APPLICATION_JSON)
				.content("{\"manufacturer_name\" : \"Boots\", \"address\" : \"12 Balamory Road\", \"phonenumber\": \"12345675}\"}"))
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.manufacturer_name", is("Boots")));
		
		
	}
}
