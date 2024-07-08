package com.example.api.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UE {
		private int id;
		private String nom;
		private List<Matiere> matieres;
		
		public UE(int id, String nom)
		{
			this.id = id;
			this.nom = nom;
			this.matieres = new ArrayList<Matiere>();
		}
		
		public int getId()
		{
			return id;
		}
		public String getNom()
		{
			return nom;
		}
		public List<Matiere> getMatieres()
		{
			return matieres;
		}
		
		public void ajoutMatiere(Matiere matiere)
		{
			matieres.add(matiere);
		}
		
		@Override
		public boolean equals(Object o) {
		
		  if (this == o)
		    return true;
		  if (!(o instanceof UE))
		    return false;
		  UE ue = (UE) o;
		  return Objects.equals(this.id, ue.getId()) && Objects.equals(this.nom, ue.getNom())
		      && Objects.equals(this.matieres, ue.getMatieres());
		}
}
