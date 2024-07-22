package v2.teamV2.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import v2.teamV2.model.Question;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurDto {

    private int id;
    private String nom;
    //private Filiere filiere;
    private String email;
    private String mdp;
    //private Taux taux;
    List<Question> questionsCertifiees;
    //private int nbQuestionsValide;
    //private Revision revision;

}
