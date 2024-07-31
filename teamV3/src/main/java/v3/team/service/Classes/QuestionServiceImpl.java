package v3.team.service.Classes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import v3.team.dtos.QuestionDto;
import v3.team.exceptions.ExceptionRessourceAbsente;
import v3.team.mapper.Interfaces.QuestionMapperImpl;
import v3.team.model.Question;
import v3.team.repositories.QuestionRepository;
import v3.team.service.Interfaces.QuestionService;


@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    final QuestionRepository qRepo;

    final QuestionMapperImpl qMap;


    public QuestionServiceImpl(QuestionRepository qRepo, QuestionMapperImpl qMap) {
        this.qRepo = qRepo;
        this.qMap = qMap;
    }

    @Override
    public QuestionDto createQuestion(QuestionDto qDto) {
        Question q = qMap.toClasse(qDto);
        System.out.println(q.getCreateur());
        Question savedQuestion = qRepo.save(q);
        return qMap.toDto(savedQuestion);
    }

    @Override
    public QuestionDto getQuestionById(int qId) {
        Question q = qRepo.findById(qId)
                .orElseThrow(()
                        -> new ExceptionRessourceAbsente("Question data is not associated with given id "+qId+"\n"));
        //System.out.println(q.toString());         Exemple d'affichage d'une question
        return qMap.toDto(q);
    }

    @Override
    public List<QuestionDto> getAllQuestions() {
        List<Question> questions = qRepo.findAll();
        //First map all the corresponding users DTO, then convert to a list
        return questions.stream().map((q) -> qMap.toDto(q))
                .collect(Collectors.toList());
    }

    @Override
    public QuestionDto updateQuestion(int qId, QuestionDto updatedQuestion) {
        Question q = qRepo.findById(qId).orElseThrow(
                () -> new ExceptionRessourceAbsente("Aucune question associée à l'id "+qId+"\n")
        );
        q.setQuestion(updatedQuestion.getQuestion());
        q.setCorrection(updatedQuestion.getCorrection());
        q.setReponses(updatedQuestion.getReponses());
        q.setIndBonneRep(updatedQuestion.getIndBonneRep());
        q.setIndice(updatedQuestion.getIndice());
        q.setCertifiee(updatedQuestion.getCertifiee());
        q.setCreateur(updatedQuestion.getCreateur());
        Question updatedQuestionObj = qRepo.save(q);

        return qMap.toDto(updatedQuestionObj);
    }

    @Override
    public void deleteQuestion(int qId) {
        Question q = qRepo.findById(qId).orElseThrow(
                () -> new ExceptionRessourceAbsente("Aucune question associée à l'id "+qId+"\n")
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

