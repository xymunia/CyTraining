package v2.teamV2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import v2.teamV2.dto.UtilisateurDto;
import v2.teamV2.service.UtilisateurService;

import java.util.List;

@RestController
@RequestMapping("api/cyusers")
public class UtilisateurController {

    @Autowired
    UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService){
        this.utilisateurService = utilisateurService;
    }

    //In postman : enter data in Body > raw and choose JSON format (in blue)
    @PostMapping
    public ResponseEntity<UtilisateurDto> createUtilisateur(@RequestBody UtilisateurDto utilisateurDto) {
        try {
            UtilisateurDto savedUtilisateur = utilisateurService.createUtilisateur(utilisateurDto);
            return new ResponseEntity<>(savedUtilisateur, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @GetMapping("{id}")
    public ResponseEntity<UtilisateurDto> getUtilisateurById(@PathVariable("id") int uId) {
        UtilisateurDto utilisateurDto = utilisateurService.getUtilisateurById(uId);
        return ResponseEntity.ok(utilisateurDto);
    }


    @GetMapping
    public ResponseEntity<List<UtilisateurDto>> getAllUtilisateurs() {
        List<UtilisateurDto> utilisateurs = utilisateurService.getAllUtilisateurs();
        return ResponseEntity.ok(utilisateurs);
    }


    //In postman : enter data in Body > raw and choose JSON format (in blue)
    //Fields are inside ""
    @PutMapping("{id}")
    public ResponseEntity<UtilisateurDto> updateUtilisateur(@PathVariable("id") int uId, @RequestBody UtilisateurDto updatedUtilisateur) {
        UtilisateurDto utilisateurDto = utilisateurService.updateUtilisateur(uId, updatedUtilisateur);
        return ResponseEntity.ok(utilisateurDto);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUtilisateur(@PathVariable("id") int uId) {
        utilisateurService.deleteUtilisateur(uId);
        return ResponseEntity.ok("Student deleted successfully.");
    }

    /*
    @Autowired
    //TODO ?
    public UtilisateurController(UtilisateurService utilisateurService){
        this.utilisateurService = utilisateurService;
    }
    @GetMapping("/utilisateur")
    public Utilisateur getUtilisateur(@RequestParam Integer id){
        //TODO
        Optional<Utilisateur> utilisateur = utilisateurService.getUtilisateur(id);
        return (Utilisateur) utilisateur.orElse(null);
    }

    @GetMapping("/utilisateurs")
    //TODO : vérifier si admin en amont
    public List<Utilisateur> getUtilisateurs(@RequestParam Integer idAdmin){
        Optional<List<Utilisateur>> utilisateur = utilisateurService.getListUtilisateur(idAdmin);// il faut probablement changer le type de retour
        return (List<Utilisateur>) utilisateur.orElse(null);
    }

    @PostMapping("/creation")
    public Utilisateur postUtilisateur(@RequestParam String nom, //@RequestParam int idFiliere, @RequestParam String email, @RequestParam String mdp){

        Optional<Utilisateur> utilisateur = utilisateurService.postUtilisateur(nom, /*idFiliere, email, mdp);
        return (Utilisateur) utilisateur.orElse(null);
    }

    @PutMapping("/update")
    public Utilisateur putUtilisateur(@RequestParam int id, @RequestParam String nom, //@RequestParam int idFiliere, @RequestParam String email, @RequestParam String mdp){
    	 Optional<Utilisateur> utilisateur = utilisateurService.putUtilisateur(id, nom, //idFiliere, email, mdp);
         return (Utilisateur) utilisateur.orElse(null);
     }

    @GetMapping("/authentification")
    public Utilisateur getAuthentification(@RequestParam String email, @RequestParam String mdp){
        Optional<Utilisateur> utilisateur = utilisateurService.getAuthentification(email, mdp);
        return (Utilisateur) utilisateur.orElse(null);
    }
    */}
