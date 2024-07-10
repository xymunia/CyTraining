package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestParam;

import com.example.api.model.Chapitre;
import com.example.api.model.Question;

public class QuestionService {
	
	
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

}
