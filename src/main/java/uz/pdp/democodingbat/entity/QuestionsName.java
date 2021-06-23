package uz.pdp.democodingbat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.democodingbat.entity.template.AbsName;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class QuestionsName extends AbsName {

    private String questionsName;

    private String helpForTheQuestions;

    @ManyToOne
    private CategoryQuestions questionsSection;
}
