package v3bis.team.enumerations;


//TODO : RETIRER NON_PROPOSEE
public enum EtatValidation {

    EN_ATTENTE("En attente"),
    VALIDEE("Validée"),
    REFUSEE("Refusée");

    String valeurEtat;

    EtatValidation(String valeurEtat) { this.valeurEtat = valeurEtat; }

    public String getValeurEtat() { return valeurEtat; }

    public void setValeurEtat(String nouvelEtat) { valeurEtat = nouvelEtat; }

}