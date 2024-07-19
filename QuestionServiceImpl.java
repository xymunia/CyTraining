package v1.team.service;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import v1.team.dtos.QuestionDto;
import v1.team.exceptions.ExceptionRessourceAbsente;
import v1.team.mapper.QuestionMapper;
import v1.team.model.Question;
import v1.team.repositories.QuestionRepository;


@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionRepository qRepo;

    public QuestionServiceImpl(QuestionRepository qRepo) {
        this.qRepo = qRepo;
    }

    @Override
    public QuestionDto createQuestion(QuestionDto qDto) {
        Question q = QuestionMapper.mapToQuestion(qDto);
        Question savedQuestion = qRepo.save(q);
        return QuestionMapper.mapToQuestionDto(savedQuestion);
    }

    @Override
    public QuestionDto getQuestionById(int qId) {
        Question q = qRepo.findById(qId)
                .orElseThrow(()
                        -> new ExceptionRessourceAbsente("Question data is not associated with given id "+qId));

        return QuestionMapper.mapToQuestionDto(q);
    }

    @Override
    public List<QuestionDto> getAllQuestions() {
        List<Question> questions = qRepo.findAll();
        //First map all the corresponding users DTO, then convert to a list
        return questions.stream().map((q) -> QuestionMapper.mapToQuestionDto(q))
                .collect(Collectors.toList());
    }

    @Override
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

    @Override
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

