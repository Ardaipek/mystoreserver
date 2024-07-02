package be.cbtw.mystore.controller;

import be.cbtw.mystore.dto.CategoryRecord;
import be.cbtw.mystore.dto.ProductRecord;
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

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void saveProductTest() throws Exception {
        CategoryRecord categoryRecord = new CategoryRecord(1, "Electronics");
        ProductRecord record = new ProductRecord(null, "iPhone", 1, new BigDecimal("1000.99"), categoryRecord);
        ProductRecord createdProduct = createProduct(record);

        assertAll(
                () -> assertEquals(record.name(), createdProduct.name()),
                () -> assertEquals(record.quantity(), createdProduct.quantity()),
                () -> assertEquals(record.price(), createdProduct.price()),
                () -> assertEquals(record.category().id(), createdProduct.category().id())
        );
    }

    private ProductRecord createProduct(ProductRecord record) throws Exception {
        MvcResult result = this.mockMvc
                .perform(post("/products")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(record))
                )
                .andExpect(status().isCreated()).andReturn();

        return objectMapper.readValue(result.getResponse().getContentAsString(), ProductRecord.class);
    }
}
