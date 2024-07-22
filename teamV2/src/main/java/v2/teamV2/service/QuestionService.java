package v2.teamV2.service;

import org.springframework.beans.factory.annotation.Autowired;
import v2.teamV2.dto.QuestionDto;
import v2.teamV2.exception.ExceptionRessourceAbsente;
import v2.teamV2.model.Question;
import v2.teamV2.mapper.QuestionMapper;

import org.springframework.stereotype.Service;
import v2.teamV2.repository.QuestionRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class QuestionService {


    @Autowired
    QuestionRepository qRepo;

    public QuestionService(QuestionRepository qRepo) {
        this.qRepo = qRepo;
    }

    public QuestionDto createQuestion(QuestionDto qDto) {
        try {
            Question q = QuestionMapper.mapToQuestion(qDto);
            Question savedQuestion = qRepo.save(q);
            return QuestionMapper.mapToQuestionDto(savedQuestion);
        } catch (Exception e) {
            System.out.println("ERREUR CREATION QUESTION REPOSITORY \n \n");
            throw new RuntimeException(e);
        }
    }

    public QuestionDto getQuestionById(int qId) {
        Question q = qRepo.findById(qId)
                .orElseThrow(()
                        -> new ExceptionRessourceAbsente("Question data is not associated with given id "+qId));

        return QuestionMapper.mapToQuestionDto(q);
    }

    public List<QuestionDto> getAllQuestions() {
        List<Question> questions = qRepo.findAll();
        //First map all the corresponding users DTO, then convert to a list
        return questions.stream().map((q) -> QuestionMapper.mapToQuestionDto(q))
                .collect(Collectors.toList());
    }

    public QuestionDto updateQuestion(int qId, QuestionDto updatedQuestion) {
        Question q = qRepo.findById(qId).orElseThrow(
                () -> new ExceptionRessourceAbsente("Aucune question associée à l'id "+qId)
        );
        q.setQuestion(updatedQuestion.getQuestion());
        q.setCorrection(updatedQuestion.getCorrection());
        q.setReponses(updatedQuestion.getReponses());
        q.setIndBonneRep(updatedQuestion.getIndBonneRep());
        q.setIndice(updatedQuestion.getIndice());
        q.setApprouvee(updatedQuestion.getApprouvee());
        q.setCreateur(updatedQuestion.getCreateur());
        Question updatedQuestionObj = qRepo.save(q);

        return QuestionMapper.mapToQuestionDto(updatedQuestionObj);
    }

    public void deleteQuestion(int qId) {
        Question q = qRepo.findById(qId).orElseThrow(
                () -> new ExceptionRessourceAbsente("Aucune question associée à l'id "+qId)
        );
        //
        qRepo.deleteById(qId);
    }

/*

    //TODO : ajust questionService
    @Override
    public QuestionDto createQuestion(QuestionDto qDto) {
        return null;
    }

    @Override
    public QuestionDto getQuestionById(int qId) {
        return null;
    }

    @Override
    public List<QuestionDto> getAllQuestions() {
        return null;
    }

    @Override
    public QuestionDto updateQuestion(int qId, QuestionDto updatedQuestion) {
        return null;
    }

    @Override
    public void deleteQuestion(int qId) {

    }
     */

}
