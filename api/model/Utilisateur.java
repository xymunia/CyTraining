package com.example.api.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Utilisateur {
	private int id;
	private String nom;
	private Filiere filiere;
	private String email;
	private String mdp;
	private Taux taux;
	private int nbQuestionsPropose;
	private int nbQuestionsValide;
	private Revision revision;
	
	// ce constructeur ne sert que pour transformer un utilisateur en admin
	public Utilisateur(Utilisateur u)
	{
		id = u.getId();
		nom = u.getNom();
		filiere = u.getFiliere();
		email = u.getEmail();
		mdp = u.getMdp();
		taux = u.getTaux();
		nbQuestionsPropose = u.getNbQuestionsPropose();
		nbQuestionsValide = u.getNbQuestionsValide();
		revision = u.getRevision();	
	} // constructeur pour post
	public Utilisateur(int id, String nom, Filiere filiere, String email, String mdp)
	{
		this.id = id;
		this.nom = nom;
		this.filiere = filiere;
		this.email = email;
		this.mdp = mdp;	
		this.nbQuestionsPropose = 0;
		this.nbQuestionsValide = 0;
		this.taux = new Taux(this);
		this.revision = null;
	} // constructeur pour utiliser les donnees
	public Utilisateur(int id, String nom, Filiere filiere, String email, String mdp, Taux taux, int nbQuestionsPropose, int nbQuestionsValide, Revision revision)
	{
		this.id = id;
		this.nom = nom;
		this.filiere = filiere;
		this.email = email;
		this.mdp = mdp;
		this.taux = taux;
		this.nbQuestionsPropose = nbQuestionsPropose;
		this.nbQuestionsValide = nbQuestionsValide;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Filiere getFiliere() {
		return filiere;
	}
	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	public Taux getTaux() {
		return taux;
	}
	public void setTaux(Taux taux) {
		this.taux = taux;
	}
	public int getNbQuestionsPropose() {
		return nbQuestionsPropose;
	}
	public void setNbQuestionsPropose(int nbQuestionsPropose) {
		this.nbQuestionsPropose = nbQuestionsPropose;
	}
	public int getNbQuestionsValide() {
		return nbQuestionsValide;
	}
	public void setNbQuestionsValide(int nbQuestionsValide) {
		this.nbQuestionsValide = nbQuestionsValide;
	}
	public Revision getRevision() {
		return revision;
	}
	public void setRevision(Revision revision) {
		this.revision = revision;
	}
	
	@Override
	public boolean equals(Object o) {
	
	  if (this == o)
	    return true;
	  if (!(o instanceof Utilisateur))
	    return false;
	  Utilisateur utilisateur = (Utilisateur) o;
	  return Objects.equals(this.id, utilisateur.id) && Objects.equals(this.nom, utilisateur.nom)
	      && Objects.equals(this.email, utilisateur.email);
	}
	
	@Override
	public int hashCode() {
	  return Objects.hash(this.id, this.nom, this.email);
	}
	
	@Override
	public String toString() {
	  return "Employee{" + "id=" + this.id + ", name='" + this.nom + '\'' + ", role='" + this.email + '\'' + '}';
	}
		
	public void modifierProfil(String nom, Filiere filiere, String email, String mdp)
	{
		this.setNom(nom);
		this.setFiliere(filiere);
		this.setEmail(email);
		this.setMdp(mdp);
	}
	
	public void ajouterQuestion()
	{
		nbQuestionsPropose++;
	}
	public void questionValide()
	{
		nbQuestionsValide++;
	}
	
	public Revision reviser(List<Question> questions)
	{
		// on gere la liste de question à partir d'un controller (jsp lequel) et on met la liste dans cette methode pour set l'attribut
		
	    this.revision = new Revision(questions, this);
		
	    return revision;
	}
	
	public void updateTaux()
	{
		// update le taux de reussite puis le taux de completion
		taux.updateTaux(revision);
		
		revision = null;
	}

}
