package com.example.api.model;

import java.util.ArrayList;
import java.util.List;

public class Chapitre {
	private String nom;
	private int id;
	private Matiere matiere;
	private List<Question> questions;
	
	public Chapitre(int id, String nom, Matiere matiere)
	{
		this.id = id;
		this.nom = nom;
		this.matiere = matiere;
		this.questions = new ArrayList<Question> ();
	}
	
	public int getId()
	{
		return id;
	}
	public String getNom()
	{
		return nom;
	}
	public List<Question> getQuestions() {
		return questions;
	}
	
	public void addQuestion(Question question)
	{
		questions.add(question);
	}

	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}
	
}
