package com.example.service;

import com.example.api.model.Filiere;
import com.example.api.model.Revision;
import com.example.api.model.Taux;
import com.example.api.model.Utilisateur;
import com.example.api.model.Admin;
import com.example.api.model.Chapitre;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UtilisateurService {
	
    private List<Utilisateur> utilisateurList;

    public UtilisateurService() {
        utilisateurList = new ArrayList<>();

        /*
        Utilisateur utilisateur1 = new Utilisateur(1,"Ida", 32, "ida@mail.com");
        Utilisateur utilisateur2 = new Utilisateur(2,"Hans", 26, "hans@mail.com");
        Utilisateur utilisateur3 = new Utilisateur(3,"Lars", 45, "lars@mail.com");
        Utilisateur utilisateur4 = new Utilisateur(4,"Ben", 32, "ben@mail.com");
        Utilisateur utilisateur5 = new Utilisateur(5,"Eva", 59, "eva@mail.com");
        

		
		lier à la bdd des utilisateurs
		faut surement ajouter bdd des questions pour faire des revisions
		ptet mm les filieres et autres jsp
		
		vaut mieux faire une classe qui charge et fais les classes de toutes les bases de données nécessaires 
		et la mettre en attribut si on veut nn??????
		
		*/
        //utilisateurList.addAll(Arrays.asList(utilisateur1,utilisateur2,utilisateur3,utilisateur4,utilisateur5));
    }

    public Optional<Utilisateur> getUtilisateur(Integer id) {
        Optional<Utilisateur> optional = Optional.empty();
        
        // faut juste faire la requete
        
        for (Utilisateur utilisateur: utilisateurList) {
            if(id == utilisateur.getId()){
                optional = Optional.of(utilisateur);
                return optional;
            }
        }
        return optional;
    }
    
    public Optional<List<Utilisateur>> getListUtilisateur(Integer id) {
        Optional<List<Utilisateur>> optional = Optional.empty();
        
        // faut verifier si c'est bien l'id d'un admin qui puisse acceder a la liste des utilisateurs
        //optional = Optional.of(repository.findAll());
        
        
        return optional;
    }
    
    public Optional<Utilisateur> putUtilisateur(int id, String nom, int idFiliere, String email, String mdp){
    	Optional<Utilisateur> optional = Optional.empty();
 	   
    	
    	////// faut aller chercher la vrai filiere avec son id j'imagine
    	Filiere filiere = null;
 	   
    	
		for (Utilisateur utilisateur: utilisateurList) {
		    if(id == utilisateur.getId()){
		  	  utilisateur.setNom(nom);
		  	  utilisateur.setEmail(email);
		  	  utilisateur.setFiliere(filiere);
		  	  utilisateur.setMdp(mdp);
		      optional = Optional.of(utilisateur);
		          
		      return optional;
		    }
		}
		
		return optional;
    }
    
    public Optional<Utilisateur> postUtilisateur(String name, int idFiliere, String email, String mdp){
 	   Optional<Utilisateur> optional = Optional.empty();
 	   
 	   // il faut verifier si il existe deja un 	   /// il faut aussi chercher la filiere correspondant au nom dans la bdd pour faire un objet au lieu d'un simple string //////
 	   Filiere Nvfiliere = null;
 	   // faire la requete correspondante dans la bdd//////////////////////////////////////
 	   Utilisateur utilisateur = new Utilisateur(utilisateurList.size(), name, Nvfiliere, email, mdp);

 	   
 	   // faire la requete correspondante dans la bdd//////////////////////////////////////
 	   
 	   optional = Optional.of(utilisateur);
 	   return optional;
    }

    
    
    
    
    public Optional<Utilisateur> getAuthentification(String email, String mdp) {
        Optional<Utilisateur> optional = Optional.empty();
        for (Utilisateur utilisateur: utilisateurList) {
            if(email == utilisateur.getEmail() && mdp == utilisateur.getMdp()){
                optional = Optional.of(utilisateur);
                return optional;
            }
        }
        return optional;
    }    
}
