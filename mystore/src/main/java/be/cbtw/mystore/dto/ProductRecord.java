package be.cbtw.mystore.dto;


import java.math.BigDecimal;

public record ProductRecord(
        Integer id,
        String name,
        Integer quantity,
        BigDecimal price,
        CategoryRecord categoryRecord
) {
}
