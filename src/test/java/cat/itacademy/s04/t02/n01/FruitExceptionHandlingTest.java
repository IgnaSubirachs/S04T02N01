package cat.itacademy.s04.t02.n01;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class FruitExceptionHandlingTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired

    private ObjectMapper objectMapper;

    @Test
    void testGetNonExistingFruit() throws Exception {
        mockMvc.perform(get("/fruit/getOne/99999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Fruit with id 99999 not found"));
    }


    @Test
    void testUpdateWithInvalidIdFormat() throws Exception {
        String json = "{\"name\": \"Orange\", \"kilogramQuantity\": 5}";

        mockMvc.perform(put("/fruit/update/notANumber")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString("Invalid type for parameter")));
    }
    @Test
    void testDeleteWithInvalidIdFormat() throws Exception {
        mockMvc.perform(delete("/fruit/delete/invalid"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString("Invalid type for parameter")));
    }
}
