package uz.pdp.democodingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.democodingbat.entity.Category;
import uz.pdp.democodingbat.entity.WebSite;
import uz.pdp.democodingbat.payload.ApiResponse;
import uz.pdp.democodingbat.payload.CategoryDto;
import uz.pdp.democodingbat.repository.CategoryRepository;
import uz.pdp.democodingbat.repository.WebSiteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    WebSiteRepository webSiteRepository;
    public List<Category> getCategory() {
        return categoryRepository.findAll();
    }

    public Category getCategory(Integer id) {
        Optional<Category> byId = categoryRepository.findById(id);
        return byId.orElse(null);
    }

    public ApiResponse addCategory(CategoryDto categoryDto) {
        Category category=new Category();
        category.setName(categoryDto.getName());
        category.setWebSite(webSiteRepository.getById(categoryDto.getWebSiteId()));
        categoryRepository.save(category);
        return new ApiResponse("Category added",true);
    }

    public ApiResponse editCategory(Integer id, CategoryDto categoryDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()){
            Category category = optionalCategory.get();
            category.setName(categoryDto.getName());
            category.setWebSite(webSiteRepository.getById(categoryDto.getWebSiteId()));
            categoryRepository.save(category);
            return new ApiResponse("Category edited",true);
        }
        return new ApiResponse("Category not found",false);
    }

    public ApiResponse deleteCategory(Integer id) {
        categoryRepository.deleteById(id);
        return new ApiResponse("Category deleted",true);
    }
}
