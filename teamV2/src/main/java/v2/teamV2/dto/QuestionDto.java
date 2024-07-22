package v2.teamV2.dto;

import lombok.*;
import v2.teamV2.model.Utilisateur;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuestionDto {

    private int id;

    private String question;

    private String correction;

    private List<String> reponses = new ArrayList<>();

    private int indBonneRep;

    private String indice;

    private int approuvee;

    Utilisateur createur;

}
