package uz.pdp.democodingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.democodingbat.entity.CategoryQuestions;
import uz.pdp.democodingbat.entity.QuestionsName;
import uz.pdp.democodingbat.entity.WebSite;
import uz.pdp.democodingbat.payload.ApiResponse;
import uz.pdp.democodingbat.payload.QuestionsNameDto;
import uz.pdp.democodingbat.repository.CategoryQuestionsRepository;
import uz.pdp.democodingbat.repository.QuestionsNameRepository;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionsNameService {
    @Autowired
    QuestionsNameRepository questionsNameRepository;
    @Autowired
    CategoryQuestionsRepository categoryQuestionsRepository;

    public List<QuestionsName> getQuestionsName() {
        return questionsNameRepository.findAll();
    }

    public QuestionsName getQuestionsName(Integer id) {
        Optional<QuestionsName> byId = questionsNameRepository.findById(id);
        return byId.orElse(null);
    }

    public ApiResponse addQuestionsName(QuestionsNameDto questionsNameDto) {
        QuestionsName questionsName=new QuestionsName();
        questionsName.setQuestionsName(questionsNameDto.getQuestionsName());
        questionsName.setHelpForTheQuestions(questionsName.getHelpForTheQuestions());
        questionsName.setQuestionsSection(categoryQuestionsRepository.getById(questionsNameDto.getQuestionsSectionId()));
        questionsNameRepository.save(questionsName);
        return new ApiResponse("QuestionsName added",true);
    }

    public ApiResponse editQuestionsName(Integer id, QuestionsNameDto questionsNameDto) {
        Optional<QuestionsName> byId = questionsNameRepository.findById(id);
        if (byId.isPresent()) {
            QuestionsName questionsName = byId.get();
            questionsName.setQuestionsName(questionsNameDto.getQuestionsName());
            questionsName.setHelpForTheQuestions(questionsName.getHelpForTheQuestions());
            questionsName.setQuestionsSection(categoryQuestionsRepository.getById(questionsNameDto.getQuestionsSectionId()));
            questionsNameRepository.save(questionsName);
            return new ApiResponse("QuestionsName edited", true);
        }
        return new ApiResponse("QuestionsName not found", true);
    }

    public ApiResponse deleteQuestionsName(Integer id) {
        questionsNameRepository.deleteById(id);
        return new ApiResponse("QuestionsName deleted",true);
    }

}
