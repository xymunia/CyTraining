package v1.team.mapper;

import org.springframework.stereotype.Component;
import v1.team.dtos.UtilisateurDto;
import v1.team.model.Question;
import v1.team.model.Utilisateur;

import java.util.ArrayList;

//Réel mapping
//@Component
public class UtilisateurMapper {


    //TODO : ajust controllers abd dto to table join
    public static UtilisateurDto mapToUtilisateurDto(Utilisateur u) {
        return new UtilisateurDto(
                u.getId(),
                u.getNom(),
                //u.getFiliere(),
                u.getEmail(),
                u.getMdp(),
                //u.getTaux(),
                u.getQuestionsCertifiees()
                //u.getNbQuestionsValide(),
                //u.getRevision()
        );
    }

    public static Utilisateur mapToUtilisateur(UtilisateurDto uDto) {
        return new Utilisateur(
                uDto.getId(),
                uDto.getNom(),
                //uDto.getFiliere(),
                uDto.getEmail(),
                uDto.getMdp(),
                //uDto.getTaux(),
                (ArrayList<Question>) uDto.getQuestionsCertifiees()
                //uDto.getNbQuestionsValide(),
                //uDto.getRevision()
        );
    }

}
