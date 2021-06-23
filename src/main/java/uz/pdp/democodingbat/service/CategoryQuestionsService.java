package uz.pdp.democodingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.democodingbat.entity.CategoryQuestions;
import uz.pdp.democodingbat.payload.ApiResponse;
import uz.pdp.democodingbat.payload.CategoryQuestionsDto;
import uz.pdp.democodingbat.repository.CategoryQuestionsRepository;
import uz.pdp.democodingbat.repository.CategoryRepository;


import java.util.List;
import java.util.Optional;

@Service
public class CategoryQuestionsService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CategoryQuestionsRepository categoryQuestionsRepository;

    public List<CategoryQuestions> getCategoryQuestions() {
        return categoryQuestionsRepository.findAll();
    }

    public CategoryQuestions getCategoryQuestions(Integer id) {
        Optional<CategoryQuestions> byId = categoryQuestionsRepository.findById(id);
        return byId.orElse(null);
    }

    public ApiResponse addCategoryQuestions(CategoryQuestionsDto categoryQuestionsDto) {
        CategoryQuestions categoryQuestions = new CategoryQuestions();
        categoryQuestions.setSuccessStar(categoryQuestionsDto.getSuccessStar());
        categoryQuestions.setDescription(categoryQuestionsDto.getDescription());
        categoryQuestions.setCategory(categoryRepository.getById(categoryQuestionsDto.getCategoryId()));
        categoryQuestionsRepository.save(categoryQuestions);
        return new ApiResponse("CategoryQuestions added", true);
    }

    public ApiResponse editCategoryQuestions(Integer id, CategoryQuestionsDto categoryQuestionsDto) {
        Optional<CategoryQuestions> optionalCategory = categoryQuestionsRepository.findById(id);
        if (optionalCategory.isPresent()) {
            CategoryQuestions categoryQuestions = optionalCategory.get();
            categoryQuestions.setSuccessStar(categoryQuestionsDto.getSuccessStar());
            categoryQuestions.setDescription(categoryQuestionsDto.getDescription());
            categoryQuestions.setCategory(categoryRepository.getById(categoryQuestionsDto.getCategoryId()));
            categoryQuestionsRepository.save(categoryQuestions);
            return new ApiResponse("CategoryQuestions edited", true);
        }
        return new ApiResponse("CategoryQuestions not found", false);
    }

    public ApiResponse deleteCategoryQuestions(Integer id) {
        categoryQuestionsRepository.deleteById(id);
        return new ApiResponse("CategoryQuestions deleted", true);
    }
}
