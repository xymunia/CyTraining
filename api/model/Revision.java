package com.example.api.model;

import java.util.List;


import java.util.ArrayList;

public class Revision {

	private Utilisateur utilisateur;
	private Chapitre chapitre;
	private List<Question> questions;
	private int idxQuestionActuelle;
	private int nbBonneRep;
	
	public Revision(List<Question> questions, Utilisateur utilisateur)
	{
		this.utilisateur = utilisateur;
		this.chapitre = questions.get(0).getChapitre();
		this.questions = questions;
		idxQuestionActuelle = 0;
		nbBonneRep = 0;
	}
	
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public Chapitre getChapitre() {
		return chapitre;
	}
	public void setChapitre(Chapitre chapitre) {
		this.chapitre = chapitre;
	}
	public List<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	public int getIdxQuestionActuelle() {
		return idxQuestionActuelle;
	}
	public void setIdxQuestionActuelle(int idxQuestionActuelle) {
		this.idxQuestionActuelle = idxQuestionActuelle;
	}
	public int getNbBonneRep() {
		return nbBonneRep;
	}
	public void setNbBonneRep(int nbBonneRep) {
		this.nbBonneRep = nbBonneRep;
	}

	public void addQuestion(Question question) {
		questions.add(question);
	}
	
	public int questionSuivante()
	{
		// si il reste au moins une question, on passe à la suivante
		if(questions.size() > idxQuestionActuelle)
		{
			setIdxQuestionActuelle(idxQuestionActuelle + 1);
			return 1;
		}
		
		utilisateur.updateTaux();
		return 0; // sinon on arrête
	}
}
