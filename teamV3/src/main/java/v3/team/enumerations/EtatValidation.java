package v3.team.enumerations;

public enum EtatValidation {

    NON_PROPOSEE("Non proposée"),
    EN_ATTENTE("En attente"),
    VALIDEE("Validée"),
    REFUSEE("Refusée");

    String valeurEtat;

    EtatValidation(String valeurEtat) { this.valeurEtat = valeurEtat; }

    public String getValeurEtat() { return valeurEtat; }

    public void setValeurEtat(String nouvelEtat) { valeurEtat = nouvelEtat; }
}
