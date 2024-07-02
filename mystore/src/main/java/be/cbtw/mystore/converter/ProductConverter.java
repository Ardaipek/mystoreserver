package be.cbtw.mystore.converter;

import be.cbtw.mystore.dto.CategoryRecord;
import be.cbtw.mystore.dto.ProductRecord;
import be.cbtw.mystore.model.Category;
import be.cbtw.mystore.model.Product;

public class ProductConverter {

    public static ProductRecord convertProductToRecord(Product product) {
        return new ProductRecord(
                product.getId(),
                product.getName(),
                product.getQuantity(),
                product.getPrice(),
                product.getCategory() != null ? new CategoryRecord(product.getCategory().getId(), product.getName()) : null
        );
    }

    public static Product convertRecordToProduct(ProductRecord record, CategoryRecord categoryRecord) {
        return new Product(record.id(), record.name(), record.quantity(), record.price(), new Category(categoryRecord.id(), categoryRecord.name()));
    }

    public static CategoryRecord convertCategoryToRecord(Category category) {
        return new CategoryRecord(
                category.getId(),
                category.getName()
        );
    }

    public static Category convertRecordToCategory(CategoryRecord record) {
        return new Category(record.id(), record.name());
    }
}
