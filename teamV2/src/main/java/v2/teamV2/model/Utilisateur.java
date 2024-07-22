package v2.teamV2.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "utilisateur")
//TODO : régler connexion BDD
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "nom")
    private String nom;

    //Clé étrangère
	/*
	@Column(name = "filière")
	private Filiere filiere;
	*/

    @Column(name = "email")
    private String email;

    @Column(name = "mot_de_passe")
    private String mdp;

    //TODO : complete in other tables
    @Column(name = "nb_questions_proposées")
    int nbQuestionsProposees;

    //Clé étrangère
	/*
	private Taux taux;
	 */

    /**
     * Association : Un utilisateur peut créer plusieurs questions
     * Création DANS L'AUTRE TABLE (question) DE LA CLÉ ÉTRANGÈRE 'id_créateur'
     * Ce champ EST 'CACHÉ' : PAS DE questions_certifiées
     *
     * Utilisation : sélection dans la table question celles dont
     * id_créateur correspond à l'id de l'utilisateur connecté
     */

    //TODO : uniD -> @ dans UNE DES DEUX ; nom clé étrangère = nom_autre_table_id
    /**
     * Enfant / child / reference
     *
     * Une collection d'objets est obligatoire pour une instance qui peut-être liée à des instances d'une autre type
     * -> faire les méthodes d'ajout dans la liste
     * Tests :
     * - jointure en récup seulement les ids des questions
     * - jointure manuelle (OK)
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createur", orphanRemoval = true)
    List<Question> questionsCertifiees = new ArrayList<>();

    //Clé étrangère
	/*
	private Revision revision;
	*/

    public Utilisateur() {
    }

    //TODO: Héritage

    // ce constructeur ne sert que pour transformer un utilisateur en admin
	/*public Utilisateur(Utilisateur u)
	{
		id = u.getId();
		nom = u.getNom();
		//filiere = u.getFiliere();
		email = u.getEmail();
		mdp = u.getMdp();
		//taux = u.getTaux();
		idsQuestionsCertifiees = u.getQuestionsCertifiees();
		//nbQuestionsValide = u.getNbQuestionsValide();
		//revision = u.getRevision();
	}*/


    // constructeur pour post
    public Utilisateur(int id, String nom, /*Filiere filiere,*/ String email, String mdp)
    {
        this.id = id;
        this.nom = nom;
        //this.filiere = filiere;
        this.email = email;
        this.mdp = mdp;
        //this.nbQuestionsValide = 0;
        //this.taux = new Taux();
        //this.revision = null;
    }


    // constructeur pour utiliser les donnees
    public Utilisateur(int id, String nom, /*Filiere filiere,*/ String email, String mdp, /*Taux taux,*/ ArrayList<Question> questionsCertifiees /*, int nbQuestionsValide, Revision revision*/)
    {
        this.id = id;
        this.nom = nom;
        //this.filiere = filiere;
        this.email = email;
        this.mdp = mdp;
        this.nbQuestionsProposees = 0;
        //this.taux = taux;
        this.questionsCertifiees = questionsCertifiees;
        //this.nbQuestionsValide = nbQuestionsValide;
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

	/*
	public Filiere getFiliere() {
		return filiere;
	}

	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}
	 */

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public int getNbQuestionsProposees() {return this.nbQuestionsProposees;}

	/*
	public Taux getTaux() {
		return taux;
	}

	public void setTaux(Taux taux) {
		this.taux = taux;
	}
	*/

    public List<Question> getQuestionsCertifiees() {
        return questionsCertifiees;
    }

	/*
	public void setQuestionsCertifiees(int questionsCertifiees) {
		this.questionsCertifiees = questionsCertifiees;
	}
	 */

	/*
	public int getNbQuestionsValide() {
		return nbQuestionsValide;
	}

	public void setNbQuestionsValide(int nbQuestionsValide) {
		this.nbQuestionsValide = nbQuestionsValide;
	}

	public Revision getRevision() {
		return revision;
	}

	public void setRevision(Revision revision) {
		this.revision = revision;
	}
	*/

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Utilisateur))
            return false;
        Utilisateur utilisateur = (Utilisateur) o;
        return Objects.equals(this.id, utilisateur.id) && Objects.equals(this.nom, utilisateur.nom)
                && Objects.equals(this.email, utilisateur.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.nom, this.email);
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + this.id + ", name='" + this.nom + '\'' + ", role='" + this.email + '\'' + '}';
    }

    public void modifierProfil(String nom, /*Filiere filiere,*/ String email, String mdp)
    {
        this.setNom(nom);
        //this.setFiliere(filiere);
        this.setEmail(email);
        this.setMdp(mdp);
    }

    public void ajouterQuestion(Question q)
    {
        questionsCertifiees.add(q);
        nbQuestionsProposees++;
    }

    /*
    public void questionValide()
    {
        nbQuestionsValide++;
    }

    public Revision reviser(List<Question> questions)
    {
        // on gere la liste de question à partir d'un controller (jsp lequel) et on met la liste dans cette methode pour set l'attribut

        this.revision = new Revision(questions, this);

        return revision;
    }

    public void updateTaux()
    {
        // update le taux de reussite puis le taux de completion
        taux.updateTaux(revision);

        revision = null;
    }
    */


}
