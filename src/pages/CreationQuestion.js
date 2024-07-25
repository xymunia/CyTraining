import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import './CreationQuestion.css';

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

function CreationQuestion() {
  const [data, setData] = useState([]);
  const [filiere, setFiliere] = useState('');
  const [ue, setUe] = useState('');
  const [matiere, setMatiere] = useState('');
  const [chapitre, setChapitre] = useState('');
  const [matieresOptions, setMatieresOptions] = useState([]);
  const [chapitresOptions, setChapitresOptions] = useState([]);
  const [errorMessage, setErrorMessage] = useState('');
  const [question, setQuestion] = useState('');
  const [options, setOptions] = useState(['', '', '', '']);
  const [correction, setCorrection] = useState('');
  const [hint, setHint] = useState('');
  const navigate = useNavigate();

  // on Get les filieres UE matiere et chapitres      /////////////////////////
  /*useEffect(() => {
    async function GetData() {
      var response = await axios.get(`http://localhost:8080/api/cyusers/`+ idUser);
      setData(response.data)
    }
    GetData()
  }, [])
    ///////////////// après il faut formater les données pour qu'elles correspondent avec les entrées du dessus quand même
    //////////////// ou alors on get les données à chaque changement avec le useEffect comme en dessous genre au lieu de juste rendre disponible tu get les données
  */

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

  const handleOptionChange = (index, value) => {
    const newOptions = [...options];
    newOptions[index] = value;
    setOptions(newOptions);
  };

  const isValidForm = () => {
    for (let i = 0; i < options.length; i++) {
      if (!options[i]) {
        setErrorMessage(`La réponse ${i + 1} doit être remplie.`);
        return false;
      }
      const radioGroup = document.getElementsByName(`reponse${i}`);
      const isChecked = Array.from(radioGroup).some((radio) => radio.checked);
      if (!isChecked) {
        setErrorMessage(`Une réponse correcte doit être sélectionnée pour la réponse ${i + 1}.`);
        return false;
      }
    }
    return true;
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!filiere || !ue || !matiere || !chapitre || !question) {
      setErrorMessage('Tous les champs doivent être remplis.');
      return;
    }

    if (!isValidForm()) {
      return;
    }

    // Submit data to the backend here, we probably need to use the user id from the auth context
    /*try {
      const response = await axios.post('http://localhost:8080/api/cyusers', { posted_data: 'example' });
      console.log('Returned data:', response);
    } catch (e) {
      console.log(`Axios request failed: ${e}`);
    }*/

    navigate('/menu', { state: { message: 'Question créée avec succès!' } });
  };

  return (
    <div className="creation-question-container">
      <h2>Créer une Question</h2>
      <form className="form-creation-question" onSubmit={handleSubmit}>
        <div>
          <label className="label-creation-question" htmlFor="filiere">
            Filière
          </label>
          <select className="select-creation-question" name="filiere" value={filiere} onChange={(e) => setFiliere(e.target.value)}>
            <option value="">Choisir une filière</option>
            {filieres.map((f, index) => (
              <option key={index} value={f}>{f}</option>
            ))}
          </select>
        
          <label className="label-creation-question">
            UE
          </label>
          <select className="select-creation-question" value={ue} onChange={(e) => setUe(e.target.value)} disabled={!filiere}>
            <option value="">Choisir une UE</option>
            {filiere && ues[filiere]?.map((u, index) => (
              <option key={index} value={u}>{u}</option>
            ))}
          </select>
        

          <label className="label-creation-question">
            Matière
          </label>
          <select className="select-creation-question" value={matiere} onChange={(e) => setMatiere(e.target.value)} disabled={!ue}>
            <option value="">Choisir une matière</option>
            {ue && matieresOptions.map((m, index) => (
              <option key={index} value={m}>{m}</option>
            ))}
          </select>
          
          <label className="label-creation-question">
            Chapitre
          </label>
          <select className="select-creation-question" value={chapitre} onChange={(e) => setChapitre(e.target.value)} disabled={!matiere}>
            <option value="">Choisir un chapitre</option>
            {matiere && chapitresOptions.map((c, index) => (
              <option key={index} value={c}>{c}</option>
            ))}
          </select>
        </div>
        <div className="titre-field-creation">
          <label htmlFor="question">Question</label>
        </div>
        <div className="reponses-field-creation">
          <textarea
            name="question"
            value={question}
            onChange={(e) => setQuestion(e.target.value)}
            
          ></textarea>
        </div>
        <div className="titre-field-creation">
          <label>Réponses possibles</label>
        </div>
          {options.map((option, index) => (
            <div className="reponses-field-creation" key={index + "div"}>  
              <textarea rows="2" cols="100" 
                key={"reponse" + index}
                name={"reponse" + index}
                value={option}
                onChange={(e) => handleOptionChange(index, e.target.value)}
                
              />
              <div>
                <input type="radio" id={"false" + index} name={"reponse" + index} />
                <label className="creation-reponse creation-fausse" htmlFor={"false" + index}>
                  ✘
                </label>

                <input type="radio" id={"true" + index} name={"reponse" + index} />
                <label className="creation-reponse creation-bonne" htmlFor={"true" + index}>
                  ✔
                </label>
              </div>
            </div>
          ))}
        
        <div className="titre-field-creation">
          <label htmlFor="correction">Correction détaillée</label>
        </div>
        <div className="reponses-field-creation">
          <textarea rows="4" cols="300" 
            name="correction"
            value={correction}
            onChange={(e) => setCorrection(e.target.value)}
            
          ></textarea>
        </div>
        
        <div className="titre-field-creation">
          <label htmlFor="indice">Indice (optionnel)</label>
        </div>
        <div className="reponses-field-creation">
          <textarea
            name="indice"
            value={hint}
            onChange={(e) => setHint(e.target.value)}
          ></textarea>
        </div>
        {errorMessage && <p className="error">{errorMessage}</p>}
        <button type="submit">Créer la Question</button>
      </form>
    </div>
  );
}

export default CreationQuestion;
