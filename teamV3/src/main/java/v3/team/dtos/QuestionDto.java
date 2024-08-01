package v3.team.dtos;

import lombok.*;
import v3.team.enumerations.EtatValidation;
import v3.team.model.Utilisateur;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuestionDto {

    int id;

    String question;

    String correction;

    List<String> reponses = new ArrayList<>();

    int indBonneRep;

    String indice;

    String etatValidation;

    int certifiee;

    Utilisateur createur;


    public QuestionDto (int id, String question, String correction, List<String> reponses, int indBonneRep, String indice, Utilisateur createur) {
        this.id = id;
        this.question = question;
        this.correction = correction;
        this.reponses = reponses;
        this.indBonneRep = indBonneRep;
        this.indice = indice;
        EtatValidation.NON_PROPOSEE.getValeurEtat();
        this.certifiee = 0;
        this.createur = createur;
    }

    public QuestionDto() {}

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getQuestion() { return question; }

    public void setQuestion(String question) { this.question = question; }

    public String getCorrection() { return correction; }

    public void setCorrection(String correction) { this.correction = correction; }

    public List<String> getReponses() { return reponses; }

    public void setReponses(List<String> reponses) { this.reponses = reponses; }

    public int getIndBonneRep() { return indBonneRep; }

    public void setIndBonneRep(int indBonneRep) { this.indBonneRep = indBonneRep; }

    public String getIndice() { return indice; }

    public void setIndice(String indice) { this.indice = indice; }

    public String getEtatValidation() { return etatValidation; }

    public void setEtatValidation(String etat) { etatValidation = etat; }

    public int getCertifiee() { return certifiee; }

    public void setCertifiee(int certifiee) { this.certifiee = certifiee; }

    public Utilisateur getCreateur() { return createur; }

    public void setCreateur(Utilisateur createur) { this.createur = createur; }
}
