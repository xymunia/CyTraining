package com.example.api.model;

import java.util.List;

import java.util.ArrayList;

public class Question {
	
	private int id;
	private String question;
	private String correction;
	private List<String> reponses;
	private int idxBonneRep;
	private String indice;
	private int approuve;
	private Chapitre chapitre;
	
	public Question(int id, String correction, int idxBonneRep, ArrayList<String> reponses, String indice, Chapitre chapitre)
	{
		this.id = id;
		this.setCorrection(correction);
		this.idxBonneRep = idxBonneRep;
		this.setReponses(reponses);
		this.setIndice(indice);
		this.setApprouve(approuve);
		this.setChapitre(chapitre);
	}
	public Question(int id, String correction, int idxBonneRep, ArrayList<String> reponses, String indice, int approuve, Chapitre chapitre)
	{
		this.id = id;
		this.setCorrection(correction);
		this.idxBonneRep = idxBonneRep;
		this.setReponses(reponses);
		this.setIndice(indice);
		this.setApprouve(approuve);
		this.setChapitre(chapitre);
	}
	
	// getters et setters
	public int getId() {
		return id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public List<String> getReponses() {
		return reponses;
	}
	public void setReponses(List<String> reponses) {
		this.reponses = reponses;
	}
	public String getCorrection() {
		return correction;
	}
	public void setCorrection(String correction) {
		this.correction = correction;
	}
	public String getIndice() {
		return indice;
	}
	public void setIndice(String indice) {
		this.indice = indice;
	}
	public int getApprouve() {
		return approuve;
	}
	public void setApprouve(int approuve) {
		this.approuve = approuve;
	}
	public Chapitre getChapitre() {
		return chapitre;
	}
	public void setChapitre(Chapitre chapitre) {
		this.chapitre = chapitre;
	}
	
	public void approuver()
	{
		setApprouve(1);
	}
	
	public boolean repondre(int rep)
	{
		return rep == idxBonneRep;
	}
}
