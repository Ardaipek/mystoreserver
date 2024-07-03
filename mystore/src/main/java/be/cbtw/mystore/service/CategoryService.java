package be.cbtw.mystore.service;

import be.cbtw.mystore.dto.CategoryRecord;
import be.cbtw.mystore.dto.ProductRecord;

public interface CategoryService {
    CategoryRecord saveCategory(ProductRecord productRecord);

}
