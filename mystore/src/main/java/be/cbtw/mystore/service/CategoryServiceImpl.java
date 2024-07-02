package be.cbtw.mystore.service;


import be.cbtw.mystore.converter.CategoryConverter;
import be.cbtw.mystore.dto.CategoryRecord;
import be.cbtw.mystore.dto.ProductRecord;
import be.cbtw.mystore.model.Category;
import be.cbtw.mystore.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public CategoryRecord getCategoryRecord(ProductRecord productRecord) {
        Category category = categoryRepository
                .findById(productRecord.categoryRecord().id())
                .orElseGet(() -> categoryRepository.save(new Category(productRecord.categoryRecord().name())));

        return CategoryConverter.convertCategoryToRecord(category);
    }


}

