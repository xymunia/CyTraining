package atilla.demo.services.Interfaces;

import atilla.demo.classes.Utilisateur;
import atilla.demo.dto.UtilisateurDto;

import java.util.stream.Stream;

public interface UtilisateurService {



    UtilisateurDto inscrire (UtilisateurDto utilisateurDto);
    UtilisateurDto inscrire1 (UtilisateurDto utilisateurDto);


    UtilisateurDto rechercherId (int id);
    Stream<UtilisateurDto> afficherAll() ;

    UtilisateurDto rechercherMail (String mail );

    UtilisateurDto mettreàJour (int id, UtilisateurDto utilisateurDto);

    void deleteUtilsateur(int id);

    void modifierUtilisateur ( int id , UtilisateurDto utilisateurDto);



}
