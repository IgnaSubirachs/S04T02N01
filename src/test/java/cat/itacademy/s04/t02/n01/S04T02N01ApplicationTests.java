package cat.itacademy.s04.t02.n01;

import com.fasterxml.jackson.databind.ObjectMapper;
import cat.itacademy.s04.t02.n01.dto.FruitRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class S04T02N01ApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void testAddFruitSuccess() throws Exception {
		FruitRequest fruit = new FruitRequest("Banana", 10);

		mockMvc.perform(post("/fruit/add")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(fruit)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.name").value("Banana"))
				.andExpect(jsonPath("$.kilogramQuantity").value(10));
	}

	@Test
	void testAddFruitValidationError() throws Exception {
		FruitRequest fruit = new FruitRequest("", -5); // nom buit i kg negatiu

		mockMvc.perform(post("/fruit/add")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(fruit)))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.name").value("Name can't be empty"))
				.andExpect(jsonPath("$.kilogramQuantity").value("Kg must be positive"));
	}
}