package com.example.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.api.model.Revision;

@Service
public class RevisionService {

    public RevisionService() {

        /*
		lier à la bdd des questions
		
		vaut mieux faire une classe qui charge et fais les classes de toutes les bases de données nécessaires 
		et la mettre en attribut si on veut nn??????
		
		*/
        //revisionList.addAll(Arrays.asList(revision1,revision2,revision3,revision4,revision5));
    }

    public Optional<Revision> postRevision(int idChapitre, int nbQuestions, int idUtilisateur) {
        Optional<Revision> optional = Optional.empty();
        
        /*
         recuperer liste des question du chapitre dans la bdd
        for (int i = 0; i < nbQuestions; i++) {
        
        	
        	//////// choisir des questions au hasard dans les questions correspondants au chapitre
        	////// faire une liste des questions puis construire l'objet revision en dehors de la boucle
        }
        
        on recupere l'utilisateur concerner et on utilise la methode reviser avec la liste de question
        */
        Revision revision = null;
        optional = Optional.of(revision);
        
        return optional;
    }
    
    public Optional<Revision> patchRevision(int idUtilisateur, int idxReponse) {
        Optional<Revision> optional = Optional.empty();
        
        /*
        	recupere la revision à partir de l'id de l'utilisateur
        	
        	repondre et passer a la question suivante
        	
        	faire la requete pour modifier la bdd
        
        */
        Revision revision = null;
        optional = Optional.of(revision);
        
        return optional;
    }

    public Optional<Revision> getRevision(int idUtilisateur) {
        Optional<Revision> optional = Optional.empty();
        
        /*
			on recupere les infos a partir de l'id de l'utilisateur
        */
        Revision revision = null;
        optional = Optional.of(revision);
        
        return optional;
    }
    
    public Optional<Revision> deleteRevision(int idUtilisateur) {
        Optional<Revision> optional = Optional.empty();
        
        /*
			on recupere les infos a partir de l'id de l'utilisateur
        	utilisateur.setRevision(null);
        	
        	puis on fait la requete
        */
        
        Revision revision = null;
        optional = Optional.of(revision);
        
        return optional;
    }
}
