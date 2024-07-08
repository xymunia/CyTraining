package com.example.api.model;

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;

public class Matiere {
	private int id;
	private  String nom;
	private UE ue;
	private List<Chapitre> chapitres;
	
	public Matiere(int id, String nom, UE ue)
	{
		this.id = id;
		this.nom = nom;
		this.ue = ue;
		chapitres = new ArrayList<Chapitre>();
	}
	
	public int getId()
	{
		return id;
	}
	public String getNom()
	{
		return nom;
	}
	public List<Chapitre> getChapitres()
	{
		return chapitres;
	}
	
	public void addChapitre(Chapitre chapitre)
	{
		chapitres.add(chapitre);
	}
	
	@Override
	public boolean equals(Object o) {
	
	  if (this == o)
	    return true;
	  if (!(o instanceof Matiere))
	    return false;
	  Matiere matiere = (Matiere) o;
	  return Objects.equals(this.id, matiere.getId()) && Objects.equals(this.nom, matiere.getNom())
	      && Objects.equals(this.chapitres, matiere.getChapitres());
	}

	public UE getUe() {
		return ue;
	}
}
