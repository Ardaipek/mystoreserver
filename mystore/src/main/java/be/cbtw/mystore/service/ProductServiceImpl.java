package be.cbtw.mystore.service;


import be.cbtw.mystore.converter.ProductConverter;
import be.cbtw.mystore.dto.ProductRecord;
import be.cbtw.mystore.model.Category;
import be.cbtw.mystore.model.Product;
import be.cbtw.mystore.repository.CategoryRepository;
import be.cbtw.mystore.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    public List<ProductRecord> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductConverter::convertProductToRecord)
                .collect(Collectors.toList());
    }

    public Optional<ProductRecord> getProductById(Integer id) {
        return productRepository.findById(id)
                .map(ProductConverter::convertProductToRecord);
    }

    public Optional<ProductRecord> createProduct(ProductRecord productRecord) {
        Optional<Category> category = categoryRepository.findById(productRecord.categoryId());
        if (category.isEmpty()) {
            return Optional.empty();
        }

        Product product = ProductConverter.convertRecordToProduct(productRecord, category.get());
        Product savedProduct = productRepository.save(product);
        return Optional.of(ProductConverter.convertProductToRecord(savedProduct));
    }

    public Optional<ProductRecord> updateProduct(Integer id, ProductRecord productRecord) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isEmpty()) {
            return Optional.empty();
        }

        Optional<Category> category = categoryRepository.findById(productRecord.categoryId());
        if (category.isEmpty()) {
            return Optional.empty();
        }

        Product productToUpdate = existingProduct.get();

        productToUpdate.setName(productRecord.name());
        productToUpdate.setQuantity(productRecord.quantity());
        productToUpdate.setPrice(productRecord.price());
        productToUpdate.setCategory(category.get());

        Product updatedProduct = productRepository.save(productToUpdate);
        return Optional.of(ProductConverter.convertProductToRecord(updatedProduct));
    }

    public boolean deleteProduct(Integer id) {
        if (!productRepository.existsById(id)) {
            return false;
        }

        productRepository.deleteById(id);
        return true;
    }
}

