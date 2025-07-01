package cat.itacademy.s04.t02.n01;

import cat.itacademy.s04.t02.n01.dto.FruitRequest;
import cat.itacademy.s04.t02.n01.model.FruitModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class FruitControllerCrudTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateAndGetAndUpdateAndDeleteFruit() throws Exception {
        // Create
        FruitRequest createRequest = new FruitRequest("Melon", 20);
        String response = mockMvc.perform(post("/fruit/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Melon"))
                .andExpect(jsonPath("$.kilogramQuantity").value(20))
                .andReturn()
                .getResponse()
                .getContentAsString();

        FruitModel created = objectMapper.readValue(response, FruitModel.class);
        Long id = created.getId();

        // Get by ID
        mockMvc.perform(get("/fruit/getOne/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Melon"));

        // Update
        FruitRequest updateRequest = new FruitRequest("UpdatedMelon", 25);
        mockMvc.perform(put("/fruit/update/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("UpdatedMelon"))
                .andExpect(jsonPath("$.kilogramQuantity").value(25));

        // Delete
        mockMvc.perform(delete("/fruit/delete/" + id))
                .andExpect(status().isOk());

        // Get after delete
        mockMvc.perform(get("/fruit/getOne/" + id))
                .andExpect(status().isNotFound());
    }

    @Test
    void testValidationError() throws Exception {
        FruitRequest invalid = new FruitRequest("", -1);
        mockMvc.perform(post("/fruit/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalid)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name").value("Name can't be empty"))
                .andExpect(jsonPath("$.kilogramQuantity").value("Kg must be positive"));
    }

    @Test
    void testUpdateNotFound() throws Exception {
        FruitRequest request = new FruitRequest("Whatever", 5);
        mockMvc.perform(put("/fruit/update/99999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Fruit with id 99999 not found"));
    }

    @Test
    void testDeleteNotFound() throws Exception {
        mockMvc.perform(delete("/fruit/delete/99999"))
                .andExpect(status().isOk()) // Canviable a .isNotFound() si canvies el servei
                .andExpect(content().string(containsString("deleted")));
    }
}