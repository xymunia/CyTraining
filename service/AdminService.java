package com.example.service;

import java.util.List;
import java.util.Optional;

import com.example.api.model.Admin;
import com.example.api.model.Chapitre;
import com.example.api.model.Utilisateur;

public class AdminService {
	
 
	 public Optional<List<Admin>> getListAdmin(int id){    ////////////////////// ca retourne une liste, un array ????
	    	Optional<List<Admin>> optional = Optional.empty();
	  	   
	    	// il faut verifier si c'est bien l'id du root admin
	  	   
	  	    //optional = Optional.of(repository.findAll());
	  	    return optional;
	 }
	 
	 public Optional<Admin> postAdmin(int idUtilisateur, int idAdmin, String chap){
	    	Optional<Admin> optional = Optional.empty();
	 	   
	    	// faut aller chercher l'utilisateur et l'admin
	    	// faut aller chercher le chapitre dans la bdd
	    	Admin admin = null;
	    	Utilisateur utilisateur = null;
	    	Chapitre chapitre = null;
	    	
			/*
				optional = Optional.of(admin.certifier(utilisateur, chapitre));
				// ajouter à la bdd et retirer l'utilisateur
				return optional;
			  
			}*/
			
			return optional;
	    }

	 
	 public Optional<Admin> removeAdmin(int id, int idMatiere){
	    	Optional<Admin> optional = Optional.empty();
	  	   
	    	// il faut verifier si c'est bien l'id du root admin
	    	// prendre le chapitre à partir de son id
	    	// admin.removeCertif(chapitre);
	  	   
	  	    // faire la requete correspondante dans la bdd//////////////////////////////////////
	  	   
	  	    //optional = Optional.of(admin);
	  	    return optional;
	 }
	 
	 public Optional<Admin> addAdmin(int id, int idMatiere){
	    	Optional<Admin> optional = Optional.empty();
	  	   
	    	// il faut verifier si c'est bien l'id du root admin
	    	// prendre le chapitre à partir de son id
	    	// admin.certifier(chapitre);
	  	   
	  	    // faire la requete correspondante dans la bdd//////////////////////////////////////
	  	   
	  	    //optional = Optional.of(admin);
	  	    return optional;
	 }
}
