package v1.team.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//TODO : définir manuellement constructeurs (avec/sans args), getters et setters
@Entity
@Table(name = "utilisateur")
public class Utilisateur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	int id;

	@Column(name = "nom")
	String nom;

	@Column(name = "email")
	String email;

	@Column(name = "mot_de_passe")
	String mdp;

	//TODO : complete in other tables
	@Column(name = "nb_questions_proposées")
	int nbQuestionsProposees;

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
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "createur", orphanRemoval = true)
	List<Question> questionsCertifiees = new ArrayList<>();

	//Clé étrangère
	/*
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
		//nbQuestionsValide = u.getNbQuestionsValide();
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
	 */
	public Utilisateur(int id, String nom, /*Filiere filiere,*/ String email, String mdp)
	{
		this.id = id;
		this.nom = nom;
		this.email = email;
		this.mdp = mdp;
		this.nbQuestionsProposees = 0;
		//this.nbQuestionsValide = 0;
		//this.filiere = filiere;
		//this.taux = new Taux();
		//this.questionsCertifiees = new ArrayList<>();
		//this.revision = new Revisions();
	}


	/**
	 * Constructeur avec clés étrangères (complétion en cours)
	 */
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
	public Filiere getFiliere() {
		return filiere;
	}

	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}
	 */

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
