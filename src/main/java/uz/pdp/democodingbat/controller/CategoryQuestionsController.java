package uz.pdp.democodingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.democodingbat.entity.CategoryQuestions;
import uz.pdp.democodingbat.payload.ApiResponse;
import uz.pdp.democodingbat.payload.CategoryQuestionsDto;
import uz.pdp.democodingbat.service.CategoryQuestionsService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categoryquestions")
public class CategoryQuestionsController {
    @Autowired
    CategoryQuestionsService categoryQuestionsService;

    @GetMapping
    public ResponseEntity<List<CategoryQuestions>> getCategoryQuestions() {
        List<CategoryQuestions> categoryQuestions = categoryQuestionsService.getCategoryQuestions();
        return ResponseEntity.ok(categoryQuestions);
    }

    @GetMapping("/{id}")
    public CategoryQuestions getCategoryQuestions(@PathVariable Integer id){
        return categoryQuestionsService.getCategoryQuestions(id);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addCategoryQuestions(@Valid @RequestBody CategoryQuestionsDto categoryQuestionsDto){
        ApiResponse apiResponse = categoryQuestionsService.addCategoryQuestions(categoryQuestionsDto);
        if (apiResponse.isSucces()){
            return ResponseEntity.status(201).body(apiResponse);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editCategoryQuestions(@PathVariable Integer id,@Valid @RequestBody CategoryQuestionsDto categoryQuestionsDto){
        ApiResponse apiResponse = categoryQuestionsService.editCategoryQuestions(id, categoryQuestionsDto);
        return ResponseEntity.status(apiResponse.isSucces()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/api/customer/{id}")
    public ResponseEntity<?> deleteCategoryQuestions(@PathVariable Integer id) {
        ApiResponse apiResponse = categoryQuestionsService.deleteCategoryQuestions(id);
        return ResponseEntity.status(apiResponse.isSucces()?202:409).body(apiResponse);

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleValidationExceptions(
            MethodArgumentNotValidException ex){
        Map<String ,String> errors=new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMassage=error.getDefaultMessage();
            errors.put(fieldName,errorMassage);
        });
        return errors;
    }
}
