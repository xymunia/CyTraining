package v3.team.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//TODO : définir manuellement constructeurs (avec/sans args), getters et setters
@Entity
@Table(name = "utilisateur")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "questionsCertifiees")
public class Utilisateur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@Column(name = "prénom")
	String prenom;

	String nom;

	String email;

	@Column(name = "mot_de_passe")
	String mdp;

	@Column(name = "nb_questions_proposées")
	int nbQuestionsProposees;

	@Column(name = "nb_questions_validées")
	int nbQuestionsValides;

	//Clé étrangère
	/*
	@Column(name = "filière")
	 Filiere filiere;
	*/

	//Clé étrangère
	/*
	Taux taux;
	*/

	/**
	 * Association : Un utilisateur peut créer plusieurs questions
	 * Création DANS L'AUTRE TABLE (question) DE LA CLÉ ÉTRANGÈRE 'id_créateur'
	 * Ce champ EST 'CACHÉ' : PAS DE questions_certifiées
	 *
	 * Utilisation : sélection dans la table question celles dont
	 * id_créateur correspond à l'id de l'utilisateur connecté

	 * Enfant / child / reference
	 *
	 * Une collection d'objets est obligatoire pour une instance qui peut-être liée à des instances d'une autre type
	 * -> faire les méthodes d'ajout dans la liste
	 * Tests :
	 * - jointure en récup seulement les ids des questions (IMPOSSIBLE)
	 * - jointure manuelle (OK)
	 */
	//TODO : MARCHE ENFIN (POST, GET (+ALL))
	//TODO : mais ajout infinis dans createur + questionsCertifiees de Utilisateur
	//TODO : AJOUTER LES METHODES D'AJOUT DE QUESTIONS, SUPPRESSION (whats'app : ultimate guide to jpa map associations)
	@OneToMany(mappedBy = "createur")
	@JsonBackReference
	@Column(unique = true)
	List<Question> questionsCertifiees = new ArrayList<>();

	//Clé étrangère
	/*
	@Column(name = "révision")
	Revision revision;
	*/

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
		idsQuestionsCertifiees = u.getIdsQuestionsCertifiees();
		//revision = u.getRevision();
	}*/

	/**
	 * Constructeur sans argument
	 *
	 * Nécessaire pour les contrôleurs
	 */
	public Utilisateur() {}

	/**
	 * Constructeur sans clés étrangères
	 *
	 * @param id nécessaire pour les APIs
	 */
	public Utilisateur(int id, String prenom, String nom, String email, String mdp)
	{
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		this.email = email;
		this.mdp = mdp;
		this.nbQuestionsProposees = 0;
		this.nbQuestionsValides = 0;
	}


	/**
	 * Constructeur avec clés étrangères (complétion en cours)
	 *
	 * @param id nécessaire pour les APIs
	 */
	public Utilisateur(int id, String prenom, String nom, String email, String mdp, ArrayList<Question> questionsCertifiees /*Filiere filiere, Taux taux, Revision revision*/)
	{
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		this.email = email;
		this.mdp = mdp;
		this.nbQuestionsProposees = 0;
		this.nbQuestionsValides = 0;
		//this.filiere = filiere;
		//this.taux = taux;
		this.questionsCertifiees = questionsCertifiees;
		//this.revision = revision;
	}

	/**
	 * Constructeur avec clés étrangères (complétion en cours)
	 *
	 * Version CLI SQL natif
	 * les ids sont générés automatiquement, inutile comme paramètre
	 */
	public Utilisateur(String prenom, String nom, String email, String mdp, ArrayList<Question> questionsCertifiees /*Filiere filiere, Taux taux, Revision revision*/)
	{
		this.prenom = prenom;
		this.nom = nom;
		this.email = email;
		this.mdp = mdp;
		this.nbQuestionsProposees = 0;
		this.nbQuestionsValides = 0;
		//this.taux = taux;
		this.questionsCertifiees = questionsCertifiees;
		//this.filiere = filiere;
		//this.revision = revision;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public String getPrenom() {return prenom;}

	public void setPrenom(String nouvPrenom) {prenom = nouvPrenom;}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

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

	public int getNbQuestionsProposees() { return this.nbQuestionsProposees; }

	public void setNbQuestionsProposees(int nbQProposees) { this.nbQuestionsProposees = nbQProposees;}

	public int getNbQuestionsValides() { return this.nbQuestionsValides;}

	public void setNbQuestionsValides(int nbQValidees) { this.nbQuestionsValides = nbQValidees;}


	/*public Filiere getFiliere() { return filiere; }


	public void setFiliere(Filiere filiere) { this.filiere = filiere; }


	public Taux getTaux() { return taux; }


	public void setTaux(Taux taux) { this.taux = taux; }*/

	public List<Question> getQuestionsCertifiees() {
		return questionsCertifiees;
	}

	public void addQuestion(Question q)
	{
		questionsCertifiees.add(q);
		nbQuestionsProposees++;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.nom, this.email);
	}

	@Override
	public String toString() {
		return "Utilisateur {" + "id = " + this.id + ", name = " + this.nom + ", email = " + this.email
				+ "mot de passe = " + this.mdp + ", nbQuestionsProposees = " + this.nbQuestionsProposees
				+ ", questionsCertifiees = " + this.questionsCertifiees + "}\n";
	}

	/*
	public void setQuestionsCertifiees(List<Question> questionsCertifiees) {
		this.questionsCertifiees = questionsCertifiees;
	}

	public Revision getRevision() { return revision; }


	public void setRevision(Revision revision) { this.revision = revision; }


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

	@Override
	public boolean equals(Object o) {
		if (o instanceof Utilisateur) {
			Utilisateur utilisateur = (Utilisateur) o;
			return this.id == utilisateur.getId();
		}
		return false;
	}

	public void modifierProfil(String nom, /*Filiere filiere,*/ String email, String mdp)
	{
		this.setNom(nom);
		//this.setFiliere(filiere);
		this.setEmail(email);
		this.setMdp(mdp);
	}


}