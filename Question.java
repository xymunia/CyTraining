package v1.team.model;

import jakarta.persistence.*;

import java.util.List;

import java.util.ArrayList;


@Entity
@Table(name = "question")
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name = "question")
	private String question;

	@Column(name = "correction")
	private String correction;

	public Utilisateur getCreateur() {
		return createur;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setReponses(List<String> reponses) {
		this.reponses = reponses;
	}

	public void setCreateur(Utilisateur createur) {
		this.createur = createur;
	}

	@Column(name = "réponses")
	private List<String> reponses = new ArrayList<>();

	@Column(name = "id_bonne_réponse")
	private int indBonneRep;

	@Column(name = "indice")
	private String indice;

	@Column(name = "validation")
	private int approuvee;

	//TODO : uniD -> @ dans UNE DES DEUX ; nom clé étrangère = nom_autre_table_id
	/**
	 * Parent / owner
	 *
	 * Une collection d'objets est obligatoire pour une instance qui peut-être liée à des instances d'une autre type
	 * -> faire les méthodes d'ajout dans la liste de questions
	 * Tests :
	 * - jointure en récup seulement les ids des questions
	 * - jointure de base avec mon lien
	 * - jointure de base avec le lien en bas de la classe Utilisateur
	 */
	//ALL : toutes les cascades
	//PERSIST : Création possible de question (hôte) même quand le créateur (champ de la référence) n'existe pas dans la BDD
	//DETACH : question (référence) est détachée de la bdd donc aucune modif possible de createur
	//MERGE : les modifs de createur (champ de la réf) dans question (table hôte) sont prise en compte dans la table utilisateur (réf)
	//REMOVE : supprimer une question (hôte) supprime un utilisateur (réf)
	//REFRESH :
	@ManyToOne(cascade = {CascadeType.PERSIST})
	@JoinColumn(name = "id_créateur")
	Utilisateur createur;


	//Clé étrangère
	//private Chapitre chapitre;

	public Question(int id, String question, String correction, List<String> reponses, int indBonneRep, String indice, int approuvee, Utilisateur createur) {
		this.id = id;
		this.question = question;
		this.correction = correction;
		this.indBonneRep = indBonneRep;
		this.reponses = reponses;
		this.indice = indice;
		this.approuvee = approuvee;
		this.createur = createur;
	}

	public Question(int id, String question, String correction, int indBonneRep, String indice, int approuvee /*, Chapitre chapitre*/) {
		this.id = id;
		this.question = question;
		this.correction = correction;
		this.indBonneRep = indBonneRep;
		this.reponses = new ArrayList<>();
		this.indice = indice;
		this.approuvee = approuvee;
		//this.chapitre = chapitre;
	}

	public Question() {
	}

	// getters et setters
	public int getId() {
		return id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}


	public String getCorrection() {
		return correction;
	}

	public void setCorrection(String correction) {
		this.correction = correction;
	}

	public int getIndBonneRep() {
		return indBonneRep;
	}

	public void setIndBonneRep(int idxBonneRep) {
		this.indBonneRep = idxBonneRep;
	}

	public String getIndice() {
		return indice;
	}

	public void setIndice(String indice) {this.indice = indice;}

	public int getApprouvee() {
		return approuvee;
	}

	public void setApprouvee(int approuve) {
		this.approuvee = approuve;
	}

	public List<String> getReponses() {return this.reponses;}

	/**
	 * Changer une réponse
	 *
	 * Avec la méthode native de Object
	 */
    /*public void setReponse (int indRep, String rep) {
		this.reponses.set(indRep, rep);
	}

	public boolean addReponse(String rep) {
		this.reponses.add(rep);

		return true;
	}*/

    /*
	public Chapitre getChapitre() {
		return chapitre;
	}

	public void setChapitre(Chapitre chapitre) {
		this.chapitre = chapitre;
	}
	 */
	public void approuver() {
		setApprouvee(1);
	}

	public boolean repondre(int rep) {
		return rep == indBonneRep;
	}


	@Override
	public String toString() {
		return "Question {" + "id = " + this.id + ", question =' " + this.question + ", correction =' " + this.correction +
				", réponses = " + this.reponses + "\n, indBonneRép = " + this.indBonneRep +", indice = " + this.indice +
				", approuvee = " + this.approuvee + ", créateur = " + this.createur + "}\n";
	}
}
