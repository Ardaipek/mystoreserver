package be.cbtw.mystore.controller;


import be.cbtw.mystore.dto.ProductRecord;
import be.cbtw.mystore.service.ProductServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductRecord> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductRecord getProductById(@PathVariable Integer id) {
        return productService.getProductById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductRecord createProduct(@RequestBody ProductRecord productRecord) {
        return productService.createProduct(productRecord);
    }

    @PutMapping("/{id}")
    public ProductRecord updateProduct(@PathVariable Integer id, @RequestBody ProductRecord productRecord) {
        return productService.updateProduct(id, productRecord);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);

    }
}
