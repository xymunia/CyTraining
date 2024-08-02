package v3.team.service.Classes;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import v3.team.dtos.QuestionDto;
import v3.team.enumerations.EtatValidation;
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

    //TODO : validation immédiate pour des questions créées par des admins
    @Override
    @Transactional
    public QuestionDto createQuestion(QuestionDto qDto) {
        Question q = qMap.toClasse(qDto);
        System.out.println(q.getCreateur());
        Question savedQuestion = qRepo.save(q);
        return qMap.toDto(savedQuestion);
    }

    //TODO : ADMIN
    @Override
    public QuestionDto getQuestionById(int qId) {
        Question q = qRepo.findById(qId)
                .orElseThrow(()
                        -> new ExceptionRessourceAbsente("Question data is not associated with given id "+qId+"\n"));
        //System.out.println(q.toString());         Exemple d'affichage d'une question
        return qMap.toDto(q);
    }

    //TODO : ADMIN + accès aux questions validées ou en attente uniquement
    @Override
    public List<QuestionDto> getAllQuestions() {
        List<Question> questions = qRepo.findAll();
        //First map all the corresponding users DTO, then convert to a list
        return questions.stream().map((q) -> qMap.toDto(q))
                .collect(Collectors.toList());
    }

    //TODO : ADMIN
    @Override
    @Transactional
    public QuestionDto updateQuestion(int qId, QuestionDto updatedQuestion) {
        Question q = qRepo.findById(qId).orElseThrow(
                () -> new ExceptionRessourceAbsente("Aucune question associée à l'id "+qId+"\n")
        );
        q.setQuestion(updatedQuestion.getQuestion());
        q.setCorrection(updatedQuestion.getCorrection());
        q.setReponses(updatedQuestion.getReponses());
        q.setIndBonneRep(updatedQuestion.getIndBonneRep());
        q.setIndice(updatedQuestion.getIndice());

        Question updatedQuestionObj = qRepo.save(q);

        return qMap.toDto(updatedQuestionObj);
    }

    //TODO : ADMIN
    @Override
    @Transactional
    public QuestionDto validerQuestion(int qId) {

        Question q = qRepo.findById(qId).orElseThrow(
                () -> new ExceptionRessourceAbsente("Aucune question associée à l'id "+qId)
        );
        Question updatedQuestionObj = q;
        if ( q.getEtatValidation().equals(EtatValidation.EN_ATTENTE.getValeurEtat()) ) {
            q.setEtatValidation(EtatValidation.VALIDEE.getValeurEtat());
            q.setCertifiee(1);
            //Update number of validated questions for existing creators
            if ( !Objects.equals(q.getCreateur(), null) ) {
                q.getCreateur().setNbQuestionsValides( q.getCreateur().getNbQuestionsValides() + 1 );
            }
            updatedQuestionObj = qRepo.save(q);
            //No need to save changes to creator thanks to the JPA relationship cascading
        } else {
            System.out.println("La question n'a pas été mise en attente de validation\n");
        }
        return qMap.toDto(updatedQuestionObj);

    }

    //TODO : ADMIN
    @Override
    @Transactional
    public QuestionDto refusValidation(int qId) {

        Question q = qRepo.findById(qId).orElseThrow(
                () -> new ExceptionRessourceAbsente("Aucune question associée à l'id "+qId)
        );

        Question updatedQuestionObj = q;
        if ( !q.getEtatValidation().equals(EtatValidation.EN_ATTENTE.getValeurEtat()) ) {
            System.out.println("La question n'a pas été mise en attente de validation\n");
        }
        else {
            q.setEtatValidation(EtatValidation.REFUSEE.getValeurEtat());
            //Update number of validated questions for existing creators that have already tried to contribute
            if ( !Objects.equals(q.getCreateur(), null) && q.getCreateur().getNbQuestionsValides() > 0 ) {
                q.getCreateur().setNbQuestionsValides( q.getCreateur().getNbQuestionsValides() - 1 );
            }
            updatedQuestionObj = qRepo.save(q);
            //No need to save changes to creator thanks to the cascading of the JPA relationship
        }
        return qMap.toDto(updatedQuestionObj);

    }

    //TODO : ADMIN
    @Override
    @Transactional
    public QuestionDto remiseAttente(int qId) {

        Question q = qRepo.findById(qId).orElseThrow(
                () -> new ExceptionRessourceAbsente("Aucune question associée à l'id "+qId)
        );
        Question updatedQuestionObj = q;
        if ( q.getEtatValidation().equals(EtatValidation.VALIDEE.getValeurEtat()) ) {
            q.setEtatValidation( EtatValidation.EN_ATTENTE.getValeurEtat() );
            q.setCertifiee(0);
            //Update number of validated questions for existing creators that have already tried to contribute
            if ( !Objects.equals(q.getCreateur(), null) && q.getCreateur().getNbQuestionsValides() > 0 ) {
                q.getCreateur().setNbQuestionsValides( q.getCreateur().getNbQuestionsValides() - 1 );
            }
            updatedQuestionObj = qRepo.save(q);
            //No need to save changes to creator thanks to the cascading of the JPA relationship
            return qMap.toDto(updatedQuestionObj);
        }

        return qMap.toDto(updatedQuestionObj);
    }

    //TODO : ADMIN
    @Override
    public void deleteQuestion(int qId) {
        Question q = qRepo.findById(qId).orElseThrow(
                () -> new ExceptionRessourceAbsente("Aucune question associée à l'id "+qId)
        );

        if ( Objects.equals(q.getCreateur(), null) ) {
            qRepo.deleteById(qId);
            System.out.println("Question was deleted successfully.\n");
        } else {
            System.out.println("SVP ne soyez pas brutal envers le créateur de la question.\n");
        };
    }


}

