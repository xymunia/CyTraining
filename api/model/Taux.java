package com.example.api.model;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Taux {
	private Map<Matiere, Float> tauxReussite;
	private Map<UE, Float> tauxCompletion;
	private List<Question> questionsRepondu;
	
	
	public Taux()
	{	
		this.tauxReussite = new HashMap<Matiere, Float>();
		this.tauxCompletion = new HashMap<UE, Float>();
		this.questionsRepondu = new ArrayList<Question>(); // tout ceci doit pouvoir s'optimiser si besoin //////////////////////////////////
	}
	
	public List<Question> getQuestionsRepondu() {
		return questionsRepondu;
	}
	public void setQuestionsRepondu(List<Question> questionsRepondu) {
		this.questionsRepondu = questionsRepondu;
	}
	public void setTauxReussite(Map<Matiere, Float> tauxReussite) {
		this.tauxReussite = tauxReussite;
	}
	public void setTauxCompletion(Map<UE, Float> tauxCompletion) {
		this.tauxCompletion = tauxCompletion;
	}
	
	public Map<UE, Float> getCompletionMap()
	{
		return tauxCompletion;
	}
	public float getCompletion(UE ue)
	{
		return tauxCompletion.get(ue);
	}
	public Map<Matiere, Float> getReussitenMap()
	{
		return tauxReussite;
	}
	public float getReussite(Matiere matiere)
	{
		return tauxReussite.get(matiere);
	}
	
	public void addTaux(Matiere matiere, Revision revision)
	{
		float taux = (float) revision.getNbBonneRep() / (float) revision.getQuestions().size();
		tauxReussite.put(matiere, taux);	
		updateCompletion(matiere.getUe());
	}
	
	public void updateTaux(Revision revision)
	{
		Matiere matiere = revision.getChapitre().getMatiere();
		
		float tauxActuel = tauxReussite.get(matiere);
		int nbQuestions = 0;
		for(Question question : questionsRepondu)
		{
			if(question.getChapitre().getMatiere() == matiere)
			{
				nbQuestions++;
			}
		}
		
		// on calcule le nombre de bonne reponses avant la seance
		int nbBonneRep = (int)(tauxActuel * nbQuestions);
		
		// ajouter les questions de revisions aux questions repondues
		questionsRepondu.addAll(revision.getQuestions());
		
		// recalculer le taux 
		float taux = (float) (nbBonneRep + revision.getNbBonneRep()) / (float) (nbQuestions + revision.getQuestions().size());
		
		tauxReussite.put(matiere, taux);
		updateCompletion(matiere.getUe());
	}
	
	public void updateCompletion(UE ue)
	{
		// calcul la moyenne du taux de reussite des matieres
		float sommeTaux = 0;
		for(Matiere matiere : ue.getMatieres())
		{
			sommeTaux += tauxReussite.get(matiere);
		}
		float taux = sommeTaux / ue.getMatieres().size();		
		tauxCompletion.put(ue, taux);
	}	
	
	public void addQuestion(Question question)
	{
		questionsRepondu.add(question);
	}
}
