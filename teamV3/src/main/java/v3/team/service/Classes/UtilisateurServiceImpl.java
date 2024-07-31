package v3.team.service.Classes;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import v3.team.dtos.QuestionDto;
import v3.team.dtos.UtilisateurDto;
import v3.team.exceptions.ExceptionRessourceAbsente;
import v3.team.mapper.Interfaces.QuestionMapperImpl;
import v3.team.mapper.Interfaces.UtilisateurMapperImpl;
import v3.team.model.Question;
import v3.team.model.Utilisateur;

import org.springframework.stereotype.Service;
import v3.team.repositories.QuestionRepository;
import v3.team.repositories.UtilisateurRepository;
import v3.team.service.Interfaces.UtilisateurService;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    //private List<Utilisateur> utilisateurList;

    @Autowired
    final UtilisateurRepository uRepo;

    final UtilisateurMapperImpl uMap;

    QuestionRepository qServRepo;

    QuestionMapperImpl qServMapper;

    @PersistenceContext
    EntityManager em;

    public UtilisateurServiceImpl(UtilisateurRepository uRepo, UtilisateurMapperImpl uMap, QuestionRepository qServRepo,
    QuestionMapperImpl qServMapper) {
        this.uRepo = uRepo;
        this.uMap = uMap;
        this.qServRepo = qServRepo;
        this.qServMapper = qServMapper;
    }

    //TODO : ajust controllers dto to table join

    @Override
    public UtilisateurDto createUtilisateur(UtilisateurDto uDto) {
        try {
            Utilisateur u = uMap.toClasse(uDto);
            Utilisateur savedUtilisateur = uRepo.save(u);
            return uMap.toDto(savedUtilisateur);
        } catch (Exception e) {
            System.out.println("ERREUR CRÉATION D'1 USER \n");
            throw new RuntimeException(e);
        }
    }

    @Override
    public UtilisateurDto getUtilisateurById(int uId) {
        Utilisateur u = uRepo.findById(uId)
                .orElseThrow(()
                        -> new ExceptionRessourceAbsente("Utilisateur data is not associated with given id "+uId));
        //System.out.println(u.toString());         Exemple d'affichage d'un user
        return uMap.toDto(u);
    }

    @Override
    public List<UtilisateurDto> getAllUtilisateurs() {
        List<Utilisateur> utilisateurs = uRepo.findAll();
        //First map all the corresponding users DTO, then convert to a list
        return utilisateurs.stream().map((u) -> uMap.toDto(u))
                .collect(Collectors.toList());
    }

    //TODO : ajust so that updating personal data only (no performance data can be updated)
    @Override
    public UtilisateurDto updateUtilisateur(int uId, UtilisateurDto updatedUtilisateur) {
        Utilisateur u = uRepo.findById(uId).orElseThrow(
                () -> new ExceptionRessourceAbsente("Aucun utilisateur associé à l'id "+uId)
        );
        //Change data with getters and setters
        u.setPrenom(updatedUtilisateur.getPrenom());
        u.setNom(updatedUtilisateur.getNom());
        u.setEmail(updatedUtilisateur.getEmail());
        u.setMdp(updatedUtilisateur.getMdp());
        //TODO : le nb de questions certifiées
//        u.setQuestionsCertifiees(updatedUtilisateur.getQuestionsCreees());
        //
        Utilisateur updatedUtilisateurObj = uRepo.save(u);

        return uMap.toDto(updatedUtilisateurObj);
    }

    @Override
    @Transactional
    public UtilisateurDto newQuestionByUser(int uId, QuestionDto qDto) {
        Utilisateur u = uRepo.findById(uId).orElseThrow(
                () -> new ExceptionRessourceAbsente("Aucun utilisateur associé à l'id "+uId)
        );
        Question q = qServMapper.toClasse(qDto);
        u.addQuestion(q);
        q.setCreateur(u);
        try {

            em.persist(q);
            em.merge(u);
            return uMap.toDto(u);

        } catch (Exception e) {
            System.out.println("ERREUR CRÉATION D'1 QUESTION \n");
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<QuestionDto> getCreatedQuestions(int uId) {
        Utilisateur u = uRepo.findById(uId).orElseThrow(
                () -> new ExceptionRessourceAbsente("Aucun utilisateur associé à l'id "+uId)
        );

        List<Question> listeQuestions = u.getQuestionsCreees();
        return listeQuestions.stream().map((q) -> qServMapper.toDto(q))
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public QuestionDto updateQuestion(int uId, int qId, QuestionDto updatedQuestion) {
        Utilisateur u = uRepo.findById(uId).orElseThrow(
                () -> new ExceptionRessourceAbsente("Aucun utilisateur associé à l'id "+uId)
        );
        //Modifications dans la table des questions
        Question updatedQuestionObj = null;
        try {
            Question q = qServRepo.findById(qId).orElseThrow(
                    () -> new ExceptionRessourceAbsente("L'utilisateur n'a pas créé de question associée à l'id "+qId)
            );
            updatedQuestionObj = qServRepo.save(q);

        } catch (ExceptionRessourceAbsente e) {
            throw new RuntimeException(e);
        }

        //Modifications dans la liste des questions
        try {
            List<Question> questions = u.getQuestionsCreees();
            for ( Question questionUser : questions ) {
                if ( questionUser.getId() == qId ) {
                    questionUser.setQuestion( updatedQuestion.getQuestion() );
                    questionUser.setCorrection( updatedQuestion.getCorrection() );
                    questionUser.setReponses( updatedQuestion.getReponses() );
                    questionUser.setQuestion( updatedQuestion.getQuestion() );
                    questionUser.setIndBonneRep( updatedQuestion.getIndBonneRep() );
                    questionUser.setIndice( updatedQuestion.getIndice() );
                    questionUser.setCertifiee( updatedQuestion.getCertifiee() );
                }
            }
            uRepo.save(u);
            return qServMapper.toDto(updatedQuestionObj);

        } catch (Exception e) {
            System.out.println("ERREUR MODIFICATION DE LA QUESTION D'ID " + qId + " DE L'UTILISATEUR " + uId +"\n");
            throw new RuntimeException(e);
        }

    }

    @Override
    @Transactional
    public void deleteUtilisateur(int uId) {
        Utilisateur u = uRepo.findById(uId).orElseThrow(
                () -> new ExceptionRessourceAbsente("Aucun utilisateur associé à l'id "+uId)
        );
        try {
            List<Question> effacerQuestions = u.getQuestionsCreees();
            for (Question userQuestion : effacerQuestions) {
                qServRepo.deleteById(userQuestion.getId());
            }
        } catch (Exception e) {
            System.out.println("ERREUR SUPPRESSION DES QUESTIONS DE L'UTILISATEUR " + uId +"\n");
            throw new RuntimeException(e);
        }
        uRepo.deleteById(uId);
    }

}
