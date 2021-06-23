package uz.pdp.democodingbat.payload;

import lombok.Data;

@Data
public class QuestionsNameDto {
    private String questionsName;

    private String helpForTheQuestions;
    private Integer questionsSectionId;
}
