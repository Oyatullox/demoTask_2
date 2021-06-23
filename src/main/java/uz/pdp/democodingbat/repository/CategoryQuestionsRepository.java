package uz.pdp.democodingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.democodingbat.entity.CategoryQuestions;

public interface CategoryQuestionsRepository extends JpaRepository<CategoryQuestions,Integer> {
}
