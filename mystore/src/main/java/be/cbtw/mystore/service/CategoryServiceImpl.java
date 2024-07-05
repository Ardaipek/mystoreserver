package be.cbtw.mystore.service;


import be.cbtw.mystore.converter.CategoryConverter;
import be.cbtw.mystore.dto.CategoryRecord;
import be.cbtw.mystore.dto.ProductRecord;
import be.cbtw.mystore.exception.EntityNotFoundException;
import be.cbtw.mystore.model.Category;
import be.cbtw.mystore.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public CategoryRecord saveCategory(ProductRecord productRecord) {

        Category category = productRecord.category().id() != null ?
                categoryRepository.findById(productRecord.category().id()).orElseThrow(() -> new EntityNotFoundException("Category not found"))
                : categoryRepository.save(new Category(null, productRecord.category().name()));


        return CategoryConverter.convertCategoryToRecord(category);
    }


}

