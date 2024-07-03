package be.cbtw.mystore.service;

import be.cbtw.mystore.dto.CategoryRecord;
import be.cbtw.mystore.dto.ProductRecord;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

@SpringBootTest
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class CategoryServiceImplTest {

    @Autowired
    CategoryServiceImpl categoryService;


    @Test
    public void testCategorySave() {
        ProductRecord record = new ProductRecord(null, "chair", 1, new BigDecimal("10.99"), new CategoryRecord(null, "Furniture"));
        categoryService.saveCategoryTest(record);
    }

}
