package be.cbtw.mystore.service;

import be.cbtw.mystore.dto.ProductRecord;

import java.util.List;

public interface ProductService {
    List<ProductRecord> getAllProducts();

    ProductRecord getProductById(Integer id);

    ProductRecord createProduct(ProductRecord productRecord);

    ProductRecord updateProduct(Integer id, ProductRecord productRecord);

    void deleteProduct(Integer id);
}
