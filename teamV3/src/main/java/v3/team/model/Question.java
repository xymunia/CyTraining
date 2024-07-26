package v3.team.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.List;

import java.util.ArrayList;


@Entity
@Table(name = "question")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "createur")
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	String question;

	String correction;

	@Column(name = "réponses")
	List<String> reponses = new ArrayList<>();

	@Column(name = "indice_bonne_réponse")
	int indBonneRep;

	String indice;

	@Column(name = "validation")
	int certifiee;

	//TODO : uniD -> @ dans UNE DES DEUX ; nom clé étrangère = nom_autre_table_id
	/**
	 * Parent / owner
	 *
	 * Une collection d'objets est obligatoire pour une instance qui peut-être liée à des instances d'une autre type
	 * -> faire les méthodes d'ajout dans la liste de questions
	 * Tests :
	 * - jointure en récup seulement les ids des questions (IMPOSSIBLE)
	 * - jointure manuelle (OK)
	 */
	//ALL : toutes les cascades
	//PERSIST : Création possible de question (hôte) même quand le créateur (champ de la référence) n'existe pas dans la BDD
	//DETACH : question (ici hôte) est détachée de la bdd donc aucune modif possible de createur
	//MERGE : les modifs de createur (champ de la réf) dans question (table hôte) sont prise en compte dans la table utilisateur (réf)
	//REMOVE : supprimer une question (hôte) supprime un utilisateur (réf)
	//REFRESH :
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JsonManagedReference
	@JoinColumn(name = "id_créateur")
	Utilisateur createur;


	//TODO : Clé étrangère
	//Chapitre chapitre;

	/**
	 * Constructeur sans argument
	 *
	 * Nécessaire pour les contrôleurs
	 */
	public Question(int id, String question, String correction, List<String> reponses, int indBonneRep, String indice, int certifiee, Utilisateur createur) {}


	/**
	 * Constructeur avec clés étrangères (complétion en cours)
	 */
	public Question(int id, String question, String correction, List<String> reponses, int indBonneRep, String indice, Utilisateur createur /*, Chapitre chapitre*/) {
		this.id = id;
		this.question = question;
		this.correction = correction;
		this.reponses = reponses;
		this.indBonneRep = indBonneRep;
		this.indice = indice;
		this.certifiee = 0;
		this.createur = createur;
		//this.chapitre = chapitre;
	}

	/**
	 * Constructeur avec clés étrangères (complétion en cours)
	 *
	 * Version CLI SQL natif : id inutile car généré automatiquement
	 */
	public Question(String question, String correction, List<String> reponses, int indBonneRep, String indice, Utilisateur createur /*, Chapitre chapitre*/) {
		this.question = question;
		this.correction = correction;
		this.reponses = reponses;
		this.indBonneRep = indBonneRep;
		this.indice = indice;
		this.certifiee = 0;
		this.createur = createur;
		//this.chapitre = chapitre;
	}

	public Question() {}


	public int getId() {
		return id;
	}


	public void setId(int id) { this.id = id; }


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

	public List<String> getReponses() { return this.reponses; }

	public void setReponses(List<String> reponses) { this.reponses = reponses; }

	public boolean repondre(int rep) { return rep == indBonneRep; }

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

	public int getCertifiee() {
		return certifiee;
	}

	public void setCertifiee(int approuve) {
		this.certifiee = approuve;
	}

	public Utilisateur getCreateur() { return createur; }

	public void setCreateur(Utilisateur createur) { this.createur = createur; }


	@Override
	public String toString() {
		return "Question {" + "id = " + this.id + ", question =' " + this.question + ", correction =' " + this.correction +
				", réponses = " + this.reponses + "\n, indBonneRép = " + this.indBonneRep +", indice = " + this.indice +
				", certifiee = " + this.certifiee + ", créateur = " + this.createur + "}\n";
	}

    /*
	public Chapitre getChapitre() { return chapitre; }

	public void setChapitre(Chapitre chapitre) { this.chapitre = chapitre; }
	 */

}