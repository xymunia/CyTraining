package com.example.service;

import java.util.Optional;

import com.example.api.model.Admin;

public class AdminService {
	
	
	 public Optional<Admin> removeAdmin(int id, int idMatiere){
	    	Optional<Admin> optional = Optional.empty();
	  	   
	    	// il faut verifier si c'est bien l'id du root admin
	    	// prendre le chapitre à partir de son id
	    	// admin.removeCertif(chapitre);
	  	   
	  	    // faire la requete correspondante dans la bdd//////////////////////////////////////
	  	   
	  	    //optional = Optional.of(admin);
	  	    return optional;
	 }
	 
	 public Optional<Admin> getAdmin(int id){    ////////////////////// ca retourne une liste, comment on fait ????????????????????
	    	Optional<Admin> optional = Optional.empty();
	  	   
	    	// il faut verifier si c'est bien l'id du root admin
	    	// prendre le chapitre à partir de son id
	    	// admin.removeCertif(chapitre);
	  	   
	  	    // faire la requete correspondante dans la bdd//////////////////////////////////////
	  	   
	  	    //optional = Optional.of(admin);
	  	    return optional;
	 }

}
