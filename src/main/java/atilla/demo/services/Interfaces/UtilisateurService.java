package atilla.demo.services.Interfaces;

import atilla.demo.classes.Admin;
import atilla.demo.classes.AdminSup;
import atilla.demo.classes.Utilisateur;
import atilla.demo.dto.AdminDto;
import atilla.demo.dto.AdminSupDto;
import atilla.demo.dto.UtilisateurDto;

import java.util.stream.Stream;

public interface UtilisateurService {



    UtilisateurDto inscrire (UtilisateurDto utilisateurDto);
    UtilisateurDto inscrire1 (UtilisateurDto utilisateurDto);

    AdminDto inscrire2 (AdminDto utilisateurDto);

    AdminSupDto inscrire3 ( AdminSupDto adminSupDto);


    UtilisateurDto rechercherId (int id);
    Stream<UtilisateurDto> afficherAll() ;

    UtilisateurDto rechercherMail (String mail );

    UtilisateurDto mettreàJour (int id, UtilisateurDto utilisateurDto);

    void deleteUtilsateur(int id);

    void modifierUtilisateur ( int id , UtilisateurDto utilisateurDto);








}
