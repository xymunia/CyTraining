package v1.team.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import v1.team.model.Question;

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
