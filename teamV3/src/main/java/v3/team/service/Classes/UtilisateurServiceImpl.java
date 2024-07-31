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

    QuestionRepository qRepo;

    QuestionMapperImpl qMap;

    @PersistenceContext
    EntityManager em;

    public UtilisateurServiceImpl(UtilisateurRepository uRepo, UtilisateurMapperImpl uMap, QuestionRepository qRepo,
    QuestionMapperImpl qMap) {
        this.uRepo = uRepo;
        this.uMap = uMap;
        this.qRepo = qRepo;
        this.qMap = qMap;
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
    public void deleteUtilisateur(int uId) {
        Utilisateur u = uRepo.findById(uId).orElseThrow(
                () -> new ExceptionRessourceAbsente("Aucun utilisateur associé à l'id "+uId)
        );
        //
        uRepo.deleteById(uId);
    }


    @Override
    @Transactional
    public UtilisateurDto newQuestionByUser(int uId, QuestionDto qDto) {
        Utilisateur u = uRepo.findById(uId).orElseThrow(
                () -> new ExceptionRessourceAbsente("Aucun utilisateur associé à l'id "+uId)
        );
        Question q = qMap.toClasse(qDto);
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
        return listeQuestions.stream().map((q) -> qMap.toDto(q))
                .collect(Collectors.toList());
    }

}
