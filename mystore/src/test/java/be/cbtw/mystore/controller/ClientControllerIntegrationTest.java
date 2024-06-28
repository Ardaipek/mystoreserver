package be.cbtw.mystore.controller;

import be.cbtw.mystore.dto.ClientDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class ClientControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void saveClientTest() throws Exception {
        ClientDto clientDto = new ClientDto();
        clientDto.setUsername("Anima");
        clientDto.setPassword("password");
        clientDto.setEmail("anima@cbtw.tech");


        MvcResult result = this.mockMvc
                .perform(post("/clients")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clientDto))
                )
                .andExpect(status().isCreated()).andReturn();

        var createdClient = objectMapper.readValue(result.getResponse().getContentAsString(), ClientDto.class);

        assertAll(
                () -> assertEquals(clientDto.getUsername(), createdClient.getUsername()),
                () -> assertEquals(clientDto.getPassword(), createdClient.getPassword()),
                () -> assertEquals(clientDto.getEmail(), createdClient.getEmail()),
                () -> assertNotNull(createdClient.getCreatedAt()),
                () -> assertNotNull(createdClient.getId())
        );

    }

    @Test
    public void updateClientTest() throws Exception {
        ClientDto clientDto = new ClientDto();
        clientDto.setUsername("Anima");
        clientDto.setPassword("password");
        clientDto.setEmail("anima@cbtw.tech");

        MvcResult saveResult = this.mockMvc
                .perform(post("/clients")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clientDto))
                )
                .andExpect(status().isCreated()).andReturn();

        var createdClientDto = objectMapper.readValue(saveResult.getResponse().getContentAsString(), ClientDto.class);

        createdClientDto.setUsername("John");

        MvcResult updateResult = this.mockMvc
                .perform(put("/clients/{id}", createdClientDto.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createdClientDto))
                )
                .andExpect(status().isOk()).andReturn();

        var updatedClient = objectMapper.readValue(updateResult.getResponse().getContentAsString(), ClientDto.class);

        assertAll(
                () -> assertEquals("John", updatedClient.getUsername()),
                () -> assertEquals(clientDto.getPassword(), updatedClient.getPassword()),  // Make sure other fields remain unchanged
                () -> assertEquals(clientDto.getEmail(), updatedClient.getEmail())
        );
    }
}
