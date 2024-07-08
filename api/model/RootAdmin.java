package com.example.api.model;


public class RootAdmin extends Admin{

	public RootAdmin(int id, String nom, Filiere filiere, String email, String mdp)
	{
		super(id, nom, filiere, email, mdp);
	}
	
	// enlever certif d'un admin
	public void removeAdmin(Admin admin, Matiere matiere)
	{
		// bdd
		admin.removeCertif(matiere);
	}
}
