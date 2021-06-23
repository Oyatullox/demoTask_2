package uz.pdp.democodingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.democodingbat.entity.WebSite;
import uz.pdp.democodingbat.payload.ApiResponse;
import uz.pdp.democodingbat.service.WebSiteService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/website")
public class WebSiteController {
    @Autowired
    WebSiteService webSiteService;

    @GetMapping
    public ResponseEntity<List<WebSite>> getWebSites() {
        List<WebSite> customers = webSiteService.getWebSites();
        return ResponseEntity.ok(customers);

    }

    @GetMapping("/{id}")
    public WebSite getWebSite(@PathVariable Integer id){
        return webSiteService.getWebSite(id);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addWebSite(@Valid @RequestBody WebSite webSite){
        ApiResponse apiResponse = webSiteService.addWebSite(webSite);
        if (apiResponse.isSucces()){
            return ResponseEntity.status(201).body(apiResponse);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editWebSite(@PathVariable Integer id,@Valid @RequestBody WebSite webSite){
        ApiResponse apiResponse = webSiteService.editWebSite(id, webSite);
        return ResponseEntity.status(apiResponse.isSucces()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/api/customer/{id}")
    public ResponseEntity<?> deleteWebSite(@PathVariable Integer id) {
        ApiResponse apiResponse = webSiteService.deleteWebSite(id);
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
