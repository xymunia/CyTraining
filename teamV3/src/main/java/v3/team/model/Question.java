package v3.team.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import v3.team.enumerations.EtatValidation;

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

	@Column(name = "état_validation")
	String etatValidation;

	@Column(name = "validation")
	int certifiee;

	//TODO : uniD -> @ dans UNE DES DEUX ; nom clé étrangère = nom_autre_table_id
	/**
	 * Parent / owner
	 */
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	//@JsonManagedReference
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
		this.etatValidation = EtatValidation.NON_PROPOSEE.getValeurEtat();
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
		this.etatValidation = EtatValidation.NON_PROPOSEE.getValeurEtat();
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

	public String infosQuestionsReponses() {
		String reponsesCreees = "";
		for (String reponse : reponses) {
			reponsesCreees += reponse + " ,";
		}
		return "[" + reponsesCreees + "]";
	}

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

	public String getEtatValidation() { return etatValidation; }

	public void setEtatValidation(String etat) { etatValidation = etat; }

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
		return "Question {" + "id = " + id + ", question = " + question + ", correction = " + correction +
				",\nréponses = " + this.infosQuestionsReponses() + ",\nindBonneRép = " + indBonneRep +", indice = " + indice +
				", état = " + this.etatValidation + ", certifiee = " + certifiee + /*", créateur = " + createur.toString() +*/ "}\n";
	}

    /*
	public Chapitre getChapitre() { return chapitre; }

	public void setChapitre(Chapitre chapitre) { this.chapitre = chapitre; }
	 */

}
