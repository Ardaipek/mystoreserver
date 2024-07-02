package be.cbtw.mystore.service;


import be.cbtw.mystore.converter.ProductConverter;
import be.cbtw.mystore.dto.ProductRecord;
import be.cbtw.mystore.exception.EntityNotFoundException;
import be.cbtw.mystore.model.Product;
import be.cbtw.mystore.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final CategoryService categoryService;


    public ProductServiceImpl(ProductRepository productRepository, CategoryServiceImpl categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }


    @Override
    public List<ProductRecord> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductConverter::convertProductToRecord)
                .collect(Collectors.toList());
    }

    @Override
    public ProductRecord getProductById(Integer id) {
        return ProductConverter.convertProductToRecord(productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product with ID: " + id + " not found")));
    }

    @Override
    public ProductRecord createProduct(ProductRecord productRecord) {
        Product product = ProductConverter.convertRecordToProduct(productRecord, categoryService.getCategoryRecord(productRecord));
        Product savedProduct = productRepository.save(product);
        return ProductConverter.convertProductToRecord(savedProduct);
    }

    @Override
    public ProductRecord updateProduct(Integer id, ProductRecord productRecord) {
        boolean existingProduct = productRepository.existsById(id);

        if (!existingProduct) {
            throw new EntityNotFoundException("Product with ID: " + id + " does not exist");
        }
        Product product = ProductConverter.convertRecordToProduct(productRecord, categoryService.getCategoryRecord(productRecord));


        Product updatedProduct = productRepository.save(product);
        return ProductConverter.convertProductToRecord(updatedProduct);
    }


    @Override
    public void deleteProduct(Integer id) {
        boolean productExists = productRepository.existsById(id);
        if (productExists) {
            productRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Client with ID " + id + " not found");

        }
    }
}

