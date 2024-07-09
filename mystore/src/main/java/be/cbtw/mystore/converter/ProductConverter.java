package be.cbtw.mystore.converter;

import be.cbtw.mystore.dto.CategoryRecord;
import be.cbtw.mystore.dto.ProductRecord;
import be.cbtw.mystore.model.Product;

public class ProductConverter {

    public static ProductRecord convertProductToRecord(Product product) {
        return new ProductRecord(
                product.getId(),
                product.getName(),
                product.getQuantity(),
                product.getPrice(),
                CategoryConverter.convertCategoryToRecord(product.getCategory()));

    }

    public static Product convertRecordToProduct(ProductRecord record, CategoryRecord categoryRecord) {
        return new Product(
                record.id(),
                record.name(),
                record.quantity(),
                record.price(),
                CategoryConverter.convertRecordToCategory(categoryRecord));
    }


}
