import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './QuestionDetails.css';

const filieres = ['Filière A', 'Filière B', 'Filière C'];
const ues = {
  'Filière A': ['UE1', 'UE2', 'UE3'],
  'Filière B': ['UE4', 'UE5', 'UE6'],
  'Filière C': ['UE7', 'UE8', 'UE9']
};
const matieres = {
  'UE1': ['Mathématiques', 'Physique', 'Chimie'],
  'UE2': ['Biologie', 'Informatique', 'Économie'],
  'UE3': ['Histoire', 'Géographie', 'Philosophie'],
  'UE4': ['Statistiques', 'Probabilités'],
  'UE5': ['Algorithmes', 'Systèmes d\'exploitation'],
  'UE6': ['Marketing', 'Gestion'],
  'UE7': ['Archéologie', 'Sociologie'],
  'UE8': ['Climatologie', 'Urbanisme'],
  'UE9': ['Psychologie', 'Linguistique']
};
const chapitres = {
  'Mathématiques': ['Algèbre', 'Analyse', 'Géométrie'],
  'Physique': ['Mécanique', 'Électromagnétisme', 'Optique'],
  'Chimie': ['Organique', 'Inorganique', 'Physique'],
  'Biologie': ['Cellulaire', 'Génétique', 'Écologie'],
  'Informatique': ['Programmation', 'Réseaux', 'Bases de données'],
  'Économie': ['Microéconomie', 'Macroéconomie', 'Finance'],
  'Histoire': ['Antiquité', 'Moyen Âge', 'Époque moderne'],
  'Géographie': ['Géopolitique', 'Environnement', 'Cartographie'],
  'Philosophie': ['Éthique', 'Logique', 'Métaphysique'],
  'Statistiques': ['Descriptive', 'Inférentielle'],
  'Probabilités': ['Discrètes', 'Continues'],
  'Algorithmes': ['Recherche', 'Tri'],
  'Systèmes d\'exploitation': ['Processus', 'Mémoire'],
  'Marketing': ['Stratégie', 'Consommateur'],
  'Gestion': ['Comptabilité', 'Finances'],
  'Archéologie': ['Méthodes', 'Sites'],
  'Sociologie': ['Théories', 'Institutions'],
  'Climatologie': ['Atmosphère', 'Oceans'],
  'Urbanisme': ['Planification', 'Développement'],
  'Psychologie': ['Cognitive', 'Clinique'],
  'Linguistique': ['Phonétique', 'Syntaxe']
};

const questionDetails = {
  id: 1,
  matiere: 'Mathématiques',
  chapitre: 'Algèbre',
  utilisateur: 'John Doe',
  dateCreation: '2023-07-10',
  question: 'Quelle est la solution de l\'équation x^2 - 4 = 0?',
  reponses: ['x = 2', 'x = -2', 'x = 0', 'Aucune de ces réponses'],
  correction: 'x = 2',
  indice: 'Pensez aux racines carrées'
};

function QuestionDetail() {
  const [data, setData] = useState([]);
  const [filiere, setFiliere] = useState('');
  const [ue, setUe] = useState('');
  const [matiere, setMatiere] = useState('');
  const [chapitre, setChapitre] = useState('');
  const [matieresOptions, setMatieresOptions] = useState([]);
  const [chapitresOptions, setChapitresOptions] = useState([]);
  const [question, setQuestion] = useState(questionDetails.question);
  const [reponses, setReponses] = useState(questionDetails.reponses);
  const [correction, setCorrection] = useState(questionDetails.correction);
  const [indice, setIndice] = useState(questionDetails.indice);

  /*useEffect(() => {
    async function GetData() { /// we probably need to use useLocation and get the id of the question we are searching with last page
      var response = await axios.get(`http://localhost:8080/api/cyusers/`+ idQuestion);
      setData(response.data)
    }
    GetData()
  }, [])*/

  useEffect(() => {
    if (ue) {
      setMatieresOptions(matieres[ue] || []);
    } else {
      setMatieresOptions([]);
    }
    setMatiere('');
    setChapitre('');
  }, [ue]);

  useEffect(() => {
    if (matiere) {
      setChapitresOptions(chapitres[matiere] || []);
    } else {
      setChapitresOptions([]);
    }
    setChapitre('');
  }, [matiere]);


  const handleInputChange = (index, value) => {
    const newReponses = [...reponses];
    newReponses[index] = value;
    setReponses(newReponses);
  };

  const handleApprove = () => {
    alert('Question approuvée');
    /*
      axios.put(corriger et approuver)
    */
  };

  const handleReject = () => {
    alert('Question refusée');
    /*
      axios.patch(desapprouver)
    */
  };

  return (
    <div className="question-details-container">
      <h2>Détail de la Question</h2>
      <form>
      <div>
          <label className="label-question-details" htmlFor="filiere">
            Filière
          </label>
          <select className="select-question-details" name="filiere" value={filiere} onChange={(e) => setFiliere(e.target.value)}>
            <option value="">Choisir une filière</option>
            {filieres.map((f, index) => (
              <option key={index} value={f}>{f}</option>
            ))}
          </select>
        
          <label className="label-question-details">
            UE
          </label>
          <select className="select-question-details" value={ue} onChange={(e) => setUe(e.target.value)} disabled={!filiere}>
            <option value="">Choisir une UE</option>
            {filiere && ues[filiere]?.map((u, index) => (
              <option key={index} value={u}>{u}</option>
            ))}
          </select>
        

          <label className="label-question-details">
            Matière
          </label>
          <select className="select-question-details" value={matiere} onChange={(e) => setMatiere(e.target.value)} disabled={!ue}>
            <option value="">Choisir une matière</option>
            {ue && matieresOptions.map((m, index) => (
              <option key={index} value={m}>{m}</option>
            ))}
          </select>
          
          <label className="label-question-details">
            Chapitre
          </label>
          <select className="select-question-details" value={chapitre} onChange={(e) => setChapitre(e.target.value)} disabled={!matiere}>
            <option value="">Choisir un chapitre</option>
            {matiere && chapitresOptions.map((c, index) => (
              <option key={index} value={c}>{c}</option>
            ))}
          </select>
        </div>
        <label>
          <div className="titre-question-details">
            Question
          </div>
          <div className="reponses-field-question-details">
            <textarea value={question} onChange={(e) => setQuestion(e.target.value)} />
          </div>
        </label>
        <label>
          <div className="titre-question-details">
            Réponses
          </div>
           {reponses.map((reponse, index) => (
            <div className="reponses-field-question-details">  
              <textarea rows ="2" cols="100" 
                key={index}
                value={reponse}
                onChange={(e) => handleInputChange(index, e.target.value)}
                required
              ></textarea>
              <div>
                
              <input type="radio" id={index} name={index} required/>
                <label className="question-details-reponse question-details-fausse" htmlFor={index}>
                  ✘
                </label>

                <input type="radio" id={index + "B"} name={index}/>
                <label className="question-details-reponse question-details-bonne" htmlFor={index + "B"}>
                ✔
                </label>
                {/*https://textkool.com/fr/symbols/tick-check-mark-symbols?utm_content=cmp-true*/}
                
                
              </div>
              
            </div>
          ))}
        </label>
        <label>
          <div className="titre-question-details">
            Correction
          </div>
          <div className="reponses-field-question-details">
            <textarea value={correction} onChange={(e) => setCorrection(e.target.value)} />
          </div>
        </label>
        <label>
          <div className="titre-question-details">
            Indice
          </div>
          <div className="reponses-field-question-details">
            <textarea value={indice} onChange={(e) => setIndice(e.target.value)} />
          </div>
        </label>
        <p>Proposé par {questionDetails.utilisateur} le {questionDetails.dateCreation}</p>
        <div className="buttons">
          <button className="refuser" type="submit" onClick={handleReject}>Refuser</button>
          <button className="approuver" type="submit" onClick={handleApprove}>Approuver</button>
        </div>
      </form>
    </div>
  );
}

export default QuestionDetail;
