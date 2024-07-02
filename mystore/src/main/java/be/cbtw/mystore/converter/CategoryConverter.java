package be.cbtw.mystore.converter;

import be.cbtw.mystore.dto.CategoryRecord;
import be.cbtw.mystore.model.Category;

public class CategoryConverter {

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
