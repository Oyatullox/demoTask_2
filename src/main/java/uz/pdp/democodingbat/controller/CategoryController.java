package uz.pdp.democodingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.democodingbat.entity.Category;
import uz.pdp.democodingbat.entity.WebSite;
import uz.pdp.democodingbat.payload.ApiResponse;
import uz.pdp.democodingbat.payload.CategoryDto;
import uz.pdp.democodingbat.service.CategoryService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getCategory() {
        List<Category> category = categoryService.getCategory();
        return ResponseEntity.ok(category);
    }

    @GetMapping("/{id}")
    public Category getCategory(@PathVariable Integer id){
        return categoryService.getCategory(id);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addCategory(@Valid @RequestBody CategoryDto categoryDto){
        ApiResponse apiResponse = categoryService.addCategory(categoryDto);
        if (apiResponse.isSucces()){
            return ResponseEntity.status(201).body(apiResponse);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editCategory(@PathVariable Integer id,@Valid @RequestBody CategoryDto categoryDto){
        ApiResponse apiResponse = categoryService.editCategory(id, categoryDto);
        return ResponseEntity.status(apiResponse.isSucces()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/api/customer/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer id) {
        ApiResponse apiResponse = categoryService.deleteCategory(id);
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
