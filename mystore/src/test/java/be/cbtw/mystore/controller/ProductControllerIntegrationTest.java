package be.cbtw.mystore.controller;

import be.cbtw.mystore.dto.CategoryRecord;
import be.cbtw.mystore.dto.ProductRecord;
import be.cbtw.mystore.util.SpringBootHelperTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class ProductControllerIntegrationTest extends SpringBootHelperTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void saveProductTest() throws Exception {
        CategoryRecord categoryRecord = new CategoryRecord(11, "Electronics");
        ProductRecord record = new ProductRecord(null, "iPhone", 1, new BigDecimal("1000.99"), categoryRecord);
        ProductRecord createdProduct = createProduct(record);

        assertAll(
                () -> assertEquals(record.name(), createdProduct.name()),
                () -> assertEquals(record.quantity(), createdProduct.quantity()),
                () -> assertEquals(record.price(), createdProduct.price()),
                () -> assertEquals(record.category().id(), createdProduct.category().id()),
                () -> assertEquals(record.category().name(), createdProduct.category().name())
        );
    }

    @Test
    public void saveProductWithNewCategoryTest() throws Exception {
        CategoryRecord categoryRecord = new CategoryRecord(null, "Furniture");
        ProductRecord record = new ProductRecord(null, "IKEA chair", 1, new BigDecimal("70.99"), categoryRecord);
        ProductRecord createdProduct = createProduct(record);

        assertAll(
                () -> assertEquals(record.name(), createdProduct.name()),
                () -> assertEquals(record.quantity(), createdProduct.quantity()),
                () -> assertEquals(record.price(), createdProduct.price()),
                () -> assertNotNull(createdProduct.category().id()),
                () -> assertEquals(record.category().name(), createdProduct.category().name())
        );
    }

    @Test
    public void updateProduct() throws Exception {
        CategoryRecord categoryRecord = new CategoryRecord(null, "Furniture");
        ProductRecord productRecord = new ProductRecord(null, "Chair", 2, new BigDecimal("60.99"), categoryRecord);

        ProductRecord createdProduct = createProduct(productRecord);
        ProductRecord productToUpdateRecord = new ProductRecord(createdProduct.id(), "Table", 5, new BigDecimal("50.00"), createdProduct.category());


        MvcResult updateResult = this.mockMvc
                .perform(put("/products/{id}", productToUpdateRecord.id())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productToUpdateRecord))
                )
                .andExpect(status().isOk()).andReturn();

        var updatedProduct = objectMapper.readValue(updateResult.getResponse().getContentAsString(), ProductRecord.class);


        assertAll(
                () -> assertEquals(productToUpdateRecord.name(), updatedProduct.name()),
                () -> assertEquals(productToUpdateRecord.quantity(), updatedProduct.quantity()),
                () -> assertEquals(productToUpdateRecord.price(), updatedProduct.price()),
                () -> assertNotNull(updatedProduct.category().id()),
                () -> assertEquals(productToUpdateRecord.category().name(), updatedProduct.category().name())
        );

    }

    @Test
    public void deleteProduct() throws Exception {
        CategoryRecord categoryRecord = new CategoryRecord(null, "Furniture");
        ProductRecord productRecord = new ProductRecord(null, "Chair", 2, new BigDecimal("60.99"), categoryRecord);

        ProductRecord createdProduct = createProduct(productRecord);


        this.mockMvc
                .perform(delete("/products/{id}", createdProduct.id())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk()).andReturn();

        this.mockMvc
                .perform(get("/products/{id}", createdProduct.id())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound()).andReturn();


    }

    @Test
    public void getProduct() throws Exception {
        CategoryRecord categoryRecord = new CategoryRecord(null, "Furniture");
        ProductRecord productRecord = new ProductRecord(null, "Chair", 2, new BigDecimal("60.99"), categoryRecord);

        ProductRecord createdProduct = createProduct(productRecord);


        MvcResult result = this.mockMvc
                .perform(get("/products/{id}", createdProduct.id())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk()).andReturn();

        var retrievedProductRecord = objectMapper.readValue(result.getResponse().getContentAsString(), ProductRecord.class);


        assertAll(
                () -> assertEquals(createdProduct.name(), retrievedProductRecord.name()),
                () -> assertEquals(createdProduct.quantity(), retrievedProductRecord.quantity()),
                () -> assertEquals(createdProduct.price(), retrievedProductRecord.price()),
                () -> assertEquals(createdProduct.id(), retrievedProductRecord.id()),
                () -> assertEquals(createdProduct.category().id(), retrievedProductRecord.category().id()),
                () -> assertEquals(createdProduct.category().name(), retrievedProductRecord.category().name())
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
