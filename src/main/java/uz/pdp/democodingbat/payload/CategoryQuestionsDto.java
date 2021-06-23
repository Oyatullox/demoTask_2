package uz.pdp.democodingbat.payload;

import lombok.Data;

@Data
public class CategoryQuestionsDto {
    private String name;
    private int successStar;
    private String description;
    private Integer categoryId;
}
