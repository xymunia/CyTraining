package v2.teamV2.mapper;

import org.springframework.stereotype.Component;
import v2.teamV2.dto.UtilisateurDto;
import v2.teamV2.model.Question;
import v2.teamV2.model.Utilisateur;

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
