package atilla.demo.classes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;






@Entity
@Table(name = "utilisateur")
public class Utilisateur {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nom")
    private String nom;
    @Column(name = "prenom")
    private String prenom;
    @Column(name ="mail", unique = true)
    private String mail;
    @Column(name="mdp")
    private String mdp;
    @Column(name = "nbQuestionsPropose")
    private int nbQuestionsPropose;
    @Column(name="nbQuestionsValide")
    private int nbQuestionsValide;
    @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "id_Filiere" )
    private Filiere filiere;
/*
    @ElementCollection
    @Column(name="taux de Reussite")
    private List<Long> tauxReussite;*/


    public Utilisateur(int id, String nom, String prenom, String mail, String mdp, int nbQuestionsPropose, int nbQuestionsValide, Filiere filiere) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.mdp = mdp;
        this.nbQuestionsPropose = nbQuestionsPropose;
        this.nbQuestionsValide = nbQuestionsValide;
        this.filiere = filiere;
    }

    public Utilisateur() {
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getMail() {
        return mail;
    }

    public String getMdp() {
        return mdp;
    }

    public int getNbQuestionsPropose() {
        return nbQuestionsPropose;
    }

    public int getNbQuestionsValide() {
        return nbQuestionsValide;
    }

    public Filiere getFiliere() {
        return filiere;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setNbQuestionsPropose(int nbQuestionsPropose) {
        this.nbQuestionsPropose = nbQuestionsPropose;
    }

    public void setNbQuestionsValide(int nbQuestionsValide) {
        this.nbQuestionsValide = nbQuestionsValide;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }


}
