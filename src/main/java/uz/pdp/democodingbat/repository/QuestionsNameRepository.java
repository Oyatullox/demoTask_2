package uz.pdp.democodingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.democodingbat.entity.QuestionsName;
import uz.pdp.democodingbat.entity.WebSite;

public interface QuestionsNameRepository extends JpaRepository<QuestionsName,Integer> {
}
