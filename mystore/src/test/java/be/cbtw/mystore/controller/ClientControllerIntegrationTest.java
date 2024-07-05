package be.cbtw.mystore.controller;

import be.cbtw.mystore.dto.ClientRecord;
import be.cbtw.mystore.util.SpringBootHelperTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class ClientControllerIntegrationTest extends SpringBootHelperTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void saveClientTest() throws Exception {
        ClientRecord record = new ClientRecord(null, "anime", "password", "anima@gmail.com", null, null);
        ClientRecord createdClient = createClient(record);

        assertAll(
                () -> assertEquals(record.username(), createdClient.username()),
                () -> assertEquals(record.password(), createdClient.password()),
                () -> assertEquals(record.email(), createdClient.email()),
                () -> assertNotNull(createdClient.createdAt()),
                () -> assertNotNull(createdClient.id())
        );
    }

    @Test
    public void updateClientTest() throws Exception {
        ClientRecord record = new ClientRecord(null, "anime", "password", "anima@gmail.com", null, null);
        ClientRecord savedClientRecord = createClient(record);

        var clientToUpdateRecord = new ClientRecord(savedClientRecord.id(), "john", savedClientRecord.password(), savedClientRecord.email(), savedClientRecord.createdAt(), savedClientRecord.lastLogin());

        MvcResult updateResult = this.mockMvc
                .perform(put("/clients/{id}", clientToUpdateRecord.id())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clientToUpdateRecord))
                )
                .andExpect(status().isOk()).andReturn();

        var updatedClient = objectMapper.readValue(updateResult.getResponse().getContentAsString(), ClientRecord.class);

        assertAll(
                () -> assertEquals(clientToUpdateRecord.username(), updatedClient.username()),
                () -> assertEquals(clientToUpdateRecord.password(), updatedClient.password()),  // Make sure other fields remain unchanged
                () -> assertEquals(clientToUpdateRecord.email(), updatedClient.email())
        );
    }

    @Test
    public void deleteClientTest() throws Exception {
        ClientRecord record = new ClientRecord(null, "anime", "password", "anima@gmail.com", null, null);
        ClientRecord savedClientRecord = createClient(record);

        this.mockMvc.perform(delete("/clients/{id}", savedClientRecord.id())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        this.mockMvc.perform(get("/clients/{id}", savedClientRecord.id())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getClientByIdTest() throws Exception {
        ClientRecord record = new ClientRecord(null, "anime", "password", "anima@gmail.com", null, null);
        ClientRecord savedClientRecord = createClient(record);

        MvcResult getResult = this.mockMvc.perform(get("/clients/{id}", savedClientRecord.id())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        var fetchedClient = objectMapper.readValue(getResult.getResponse().getContentAsString(), ClientRecord.class);

        assertAll(
                () -> assertEquals(savedClientRecord.id(), fetchedClient.id()),
                () -> assertEquals(savedClientRecord.username(), fetchedClient.username()),
                () -> assertEquals(savedClientRecord.email(), fetchedClient.email())
        );
    }

    private ClientRecord createClient(ClientRecord record) throws Exception {
        MvcResult result = this.mockMvc
                .perform(post("/clients")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(record))
                )
                .andExpect(status().isCreated()).andReturn();

        return objectMapper.readValue(result.getResponse().getContentAsString(), ClientRecord.class);
    }
}
