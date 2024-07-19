package v1.team.service;


import org.springframework.stereotype.Service;
import v1.team.dtos.UtilisateurDto;

import java.util.List;

@Service
public interface UtilisateurService {

    UtilisateurDto createUtilisateur(UtilisateurDto uDto);

    UtilisateurDto getUtilisateurById(int uId);

    List<UtilisateurDto> getAllUtilisateurs();

    UtilisateurDto updateUtilisateur(int uId, UtilisateurDto updatedUtilisateur);

    void deleteUtilisateur(int uId);


    /*
    public Optional<List<Question>> getListQuestion(int id){    ////////////////////// ca retourne une liste, un array ????
        Optional<List<Question>> optional = Optional.empty();

        // il faut verifier si c'est bien l'id d'un admin

        // faire la requete correspondante dans la bdd//////////////////////////////////////

        //optional = Optional.of(repository.findAll());
        return optional;
    }


    public Optional<Question> postQuestion(int idUtilisateur, String correction, int idxBonneRep,
                                           String[] reponses, String indice, int idChapitre){

        Optional<Question> optional = Optional.empty();

        // chercher l'utilisateur et faire +1 à son nombre de question propose

        ArrayList<String> reponsesArray = new ArrayList<String> ();

        for(int i = 0; i < reponses.length; i++)
        {
            reponsesArray.add(reponses[i]);
        }

        // faire la requete correspondante dans la bdd//////////////////////////////////////
        int id = 1; // a chercher avc bdd
        Chapitre chapitre = null; // a chercher ds bdd a partir de l'idChapitre
        Question question = new Question(id , correction, idxBonneRep, reponsesArray, indice, chapitre);

        optional = Optional.of(question);
        return optional;
    }

    public Optional<Question> putQuestion(int idUtilisateur, String correction, int idxBonneRep,
                                          String[] reponses, String indice, int idChapitre){

        Optional<Question> optional = Optional.empty();

        // chercher l'admin et faire +1 à son nombre de question valide

        ArrayList<String> reponsesArray = new ArrayList<String> ();

        for(int i = 0; i < reponses.length; i++)
        {
            reponsesArray.add(reponses[i]);
        }

        // faire la requete correspondante dans la bdd//////////////////////////////////////
        int id = 1; // a chercher avc bdd
        Chapitre chapitre = null; // a chercher ds bdd a partir de l'idChapitre
        Question question = new Question(id , correction, idxBonneRep, reponsesArray, indice, chapitre);

        optional = Optional.of(question);
        return optional;
    }
    */

}
