package com.example.api.controller;

import com.example.api.model.Revision;
import com.example.api.model.Utilisateur;
import com.example.service.RevisionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class RevisionController {

	private final RevisionService revisionService;

    @Autowired
    public RevisionController(RevisionService revisionService){
        this.revisionService = revisionService;
    }
    
    @PostMapping("/postrevision")
    public Revision postRevision(@RequestParam int idChapitre, @RequestParam int nbQuestions, @RequestParam int idUtilisateur){
        Optional<Revision> revision = revisionService.postRevision(idChapitre, nbQuestions, idUtilisateur);
        return (Revision) revision.orElse(null);
    }
    
    @PostMapping("/patchrevision")
    public Revision patchRevision(@RequestParam int idxReponse, @RequestParam int idUtilisateur){
        Optional<Revision> revision = revisionService.patchRevision(idxReponse, idUtilisateur);
        return (Revision) revision.orElse(null);
    }
    
    @GetMapping("/revision")
    public Revision getRevision(@RequestParam int idUtilisateur){
        Optional<Revision> revision = revisionService.getRevision(idUtilisateur);
        return (Revision) revision.orElse(null);
    }
    
    @DeleteMapping("/deleterevision")
    public Revision deleteRevision(@RequestParam int idUtilisateur){
        Optional<Revision> revision = revisionService.deleteRevision(idUtilisateur);
        return (Revision) revision.orElse(null);
    }
    
    // reponse
    
    
}