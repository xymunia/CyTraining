package atilla.demo.classes;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Objects;


@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("admin")
@SuperBuilder
@Data
public class Admin extends Utilisateur{
   //private List<Matiere> certifs;

    private int nbApprouve;

    public Admin(int id, String nom, String prenom, String mail, String mdp, int nbQuestionsPropose, int nbQuestionsValide, Filiere filiere, int nbApprouve) {
        super(id, nom, prenom, mail, mdp, nbQuestionsPropose, nbQuestionsValide, filiere);

        this.nbApprouve = nbApprouve;
    }

    public Admin() {
    }

    public Admin(int nbApprouve) {
        this.nbApprouve = nbApprouve;
    }

    public int getNbApprouve() {
        return nbApprouve;
    }

    public void setNbApprouve(int nbApprouve) {
        this.nbApprouve = nbApprouve;
    }

   /* public Admin(UtilisateurBuilder<?, ?> b, int nbApprouve) {
        super(b);
        this.nbApprouve = nbApprouve;
    }*/


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Admin admin)) return false;
        if (!super.equals(o)) return false;
        return nbApprouve == admin.nbApprouve;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nbApprouve);
    }
}
