package com.example.api.controller;

import com.example.api.model.Filiere;
import com.example.api.model.Revision;
import com.example.api.model.Taux;
import com.example.api.model.Utilisateur;
import com.example.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService){
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/utilisateur")
    public Utilisateur getUser(@RequestParam Integer id){
        Optional<Utilisateur> user = utilisateurService.getUtilisateur(id);
        return (Utilisateur) user.orElse(null);
    }
    
    @PostMapping("/creation")
    public Utilisateur postUser(@RequestParam String nom, @RequestParam int idFiliere, @RequestParam String email, @RequestParam String mdp){
    	
        Optional<Utilisateur> user = utilisateurService.postUtilisateur(nom, idFiliere, email, mdp);
        return (Utilisateur) user.orElse(null);
    }
    
    @PutMapping("/update")
    public Utilisateur put(@RequestParam int id, @RequestParam String nom, @RequestParam int idFiliere, @RequestParam String email, @RequestParam String mdp){
    	
    	 Optional<Utilisateur> user = utilisateurService.putUtilisateur(id, nom, idFiliere, email, mdp);
         return (Utilisateur) user.orElse(null);
     }
    
    @GetMapping("/authentification")
    public Utilisateur getAuthentification(@RequestParam String email, @RequestParam String mdp){
        Optional<Utilisateur> user = utilisateurService.getAuthentification(email, mdp);
        return (Utilisateur) user.orElse(null);
    }
    
    
}
