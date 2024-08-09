package atilla.demo.classes;


import jakarta.persistence.DiscriminatorValue;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@DiscriminatorValue("adminSup")
@EqualsAndHashCode(callSuper=true)
public class AdminSup extends Utilisateur {

    private int code ;


    /*public AdminSup(UtilisateurBuilder<?, ?> b) {
        super(b);
    }*/

    public AdminSup(int id, String nom, String prenom, String mail, String mdp, int nbQuestionsPropose, int nbQuestionsValide, Filiere filiere, int code) {
        super(id, nom, prenom, mail, mdp, nbQuestionsPropose, nbQuestionsValide, filiere);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public AdminSup(int code) {
        this.code = code;
    }

    public AdminSup() {
    }



}
