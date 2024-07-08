package com.example.api.controller;


import com.example.api.model.Filiere;
import com.example.api.model.Question;
import com.example.api.model.Utilisateur;
import com.example.service.QuestionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Optional;

@RestController
public class QuestionController {
	
	private final QuestionService questionService;
	
	
	// il reste certifier des question (putmapping psk l'admin peut corriger toute la question)

	@Autowired
    public QuestionController(QuestionService questionService){
        this.questionService = questionService;
    }
	
	@PostMapping("/postquestion")
	public Question postQuestion(@RequestParam int idUtilisateur, @RequestParam String correction, @RequestParam int idxBonneRep, 
			@RequestParam String[] reponses, @RequestParam String indice, @RequestParam int idChapitre)
	{		
		Optional<Question> question= questionService.postQuestion(idUtilisateur, correction, idxBonneRep, reponses, indice, idChapitre);
		return (Question) question.orElse(null);
	}
	
	@PatchMapping("/putquestion")
	public Question patchQuestion(@RequestParam int idUtilisateur, @RequestParam String correction, @RequestParam int idxBonneRep, 
			@RequestParam String[] reponses, @RequestParam String indice, @RequestParam int idChapitre)
	{
		Optional<Question> question = questionService.putQuestion(idUtilisateur, correction, idxBonneRep, reponses, indice, idChapitre);
        return (Question) question.orElse(null);
	}
	
}
