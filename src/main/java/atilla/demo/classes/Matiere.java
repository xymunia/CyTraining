package atilla.demo.classes;

import jakarta.persistence.ManyToOne;

public class Matiere {
    private int id;
    private  String nom;

   // @ManyToOne
    //private UE ue;

    public Matiere(int id, String nom/*, UE ue*/) {
        this.id = id;
        this.nom = nom;
      //  this.ue = ue;
    }

    public Matiere() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    /*public UE getUe() {
        return ue;
    }*/

    /*public void setUe(UE ue) {
        this.ue = ue;
    }*/
}
