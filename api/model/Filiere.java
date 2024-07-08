package com.example.api.model;

import java.util.List;


import java.util.ArrayList;

public class Filiere {
	private int id;
	private List<UE> ue;
	private String nom;
	
	public Filiere(int id, String nom)
	{
		this.id = id;
		this.nom = nom;
		this.ue = new ArrayList<UE>();
	}
	
	public void ajoutUE(UE ue)
	{
		this.ue.add(ue);
	}
	
	public int getId()
	{
		return id;
	}
	
	public String getNom()
	{
		return nom;
	}
}
