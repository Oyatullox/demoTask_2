package uz.pdp.democodingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.democodingbat.entity.QuestionsName;
import uz.pdp.democodingbat.payload.ApiResponse;
import uz.pdp.democodingbat.payload.QuestionsNameDto;
import uz.pdp.democodingbat.repository.QuestionsNameRepository;
import uz.pdp.democodingbat.service.QuestionsNameService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/questionsname")
public class QuestionsNameController {
    @Autowired
    QuestionsNameService questionsNameService;

    @GetMapping
    public ResponseEntity<List<QuestionsName>> getQuestionsName() {
        List<QuestionsName> customers = questionsNameService.getQuestionsName();
        return ResponseEntity.ok(customers);

    }

    @GetMapping("/{id}")
    public QuestionsName getQuestionsName(@PathVariable Integer id){
        return questionsNameService.getQuestionsName(id);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addQuestionsName(@Valid @RequestBody QuestionsNameDto questionsNameDto){
        ApiResponse apiResponse = questionsNameService.addQuestionsName(questionsNameDto);
        if (apiResponse.isSucces()){
            return ResponseEntity.status(201).body(apiResponse);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editQuestionsName(@PathVariable Integer id,@Valid @RequestBody QuestionsNameDto questionsNameDto){
        ApiResponse apiResponse = questionsNameService.editQuestionsName(id, questionsNameDto);
        return ResponseEntity.status(apiResponse.isSucces()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/api/customer/{id}")
    public ResponseEntity<ApiResponse> deleteQuestionsName(@PathVariable Integer id) {
        ApiResponse apiResponse = questionsNameService.deleteQuestionsName(id);
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
