package com.example.api.model;

import java.util.List;

import java.util.ArrayList;

public class Admin extends Utilisateur{

	private List<Matiere> certifs;
	private int nbApprouve;
	
	public Admin(Utilisateur u)
	{
		super(u);
		certifs = new ArrayList<Matiere>();
		nbApprouve = 0;		
	}
	public Admin(int id, String nom, Filiere filiere, String email, String mdp)
	{
		super(id, nom, filiere, email, mdp);
		certifs = new ArrayList<Matiere>();
		nbApprouve = 0;
	}
	
	public int getNbApprouve() {
		return nbApprouve;
	}
	public void setNbApprouve(int nbApprouve) {
		this.nbApprouve = nbApprouve;
	}
	public void addCertif(Matiere matiere)
	{
		certifs.add(matiere);
	}
	public void removeCertif(Matiere matiere)
	{
		certifs.remove(matiere);
	}
	
	public int approuverQuestion(Question question)
	{
		// si le chapitre de la matiere est une certif de l'admin
		if(certifs.contains(question.getChapitre().getMatiere())){
			question.approuver();
			return 1;			
		}
		return 0;
	}
	
	public void certifier(Matiere matiere, Admin admin)
	{
		if(certifs.contains(matiere))
		{
			admin.addCertif(matiere);
		}
	}
	
	public Admin certifier(Matiere matiere, Utilisateur utilisateur)
	{
		Admin nouveauAdmin = null;
		if(certifs.contains(matiere))
		{
			nouveauAdmin = new Admin(utilisateur);
			nouveauAdmin.addCertif(matiere);
			utilisateur = null;
		}
		return nouveauAdmin;
	}
}
