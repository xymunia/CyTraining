package com.example.api.controller;


import com.example.api.model.Utilisateur;
import com.example.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService){
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/utilisateur")
    public Utilisateur getUtilisateur(@RequestParam Integer id){
        Optional<Utilisateur> utilisateur = utilisateurService.getUtilisateur(id);
        return (Utilisateur) utilisateur.orElse(null);
    }
    
    @GetMapping("/utilisateurs")
    public List<Utilisateur> getUtilisateurs(@RequestParam Integer idAdmin){
        Optional<List<Utilisateur>> utilisateur = utilisateurService.getListUtilisateur(idAdmin);// il faut probablement changer le type de retour
        return (List<Utilisateur>) utilisateur.orElse(null);
    }
    
    @PostMapping("/creation")
    public Utilisateur postUtilisateur(@RequestParam String nom, @RequestParam int idFiliere, @RequestParam String email, @RequestParam String mdp){
    	
        Optional<Utilisateur> utilisateur = utilisateurService.postUtilisateur(nom, idFiliere, email, mdp);
        return (Utilisateur) utilisateur.orElse(null);
    }
    
    @PutMapping("/update")
    public Utilisateur putUtilisateur(@RequestParam int id, @RequestParam String nom, @RequestParam int idFiliere, @RequestParam String email, @RequestParam String mdp){
    	 Optional<Utilisateur> utilisateur = utilisateurService.putUtilisateur(id, nom, idFiliere, email, mdp);
         return (Utilisateur) utilisateur.orElse(null);
     }
    
    @GetMapping("/authentification")
    public Utilisateur getAuthentification(@RequestParam String email, @RequestParam String mdp){
        Optional<Utilisateur> utilisateur = utilisateurService.getAuthentification(email, mdp);
        return (Utilisateur) utilisateur.orElse(null);
    }
    
    
}
