import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import './Revision.css';

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

function Revision() {
  const [data, setData] = useState([]);
  const [filiere, setFiliere] = useState('');
  const [ue, setUe] = useState('');
  const [matiere, setMatiere] = useState('');
  const [chapitre, setChapitre] = useState('');
  const [nombreQuestions, setNombreQuestions] = useState(1);
  const [matieresOptions, setMatieresOptions] = useState([]);
  const [chapitresOptions, setChapitresOptions] = useState([]);
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

  const handleStartRevision = () => {
    if (filiere && ue && matiere && chapitre && nombreQuestions) {
      // Submit data to the backend here, we probably need to use the user id from the auth context
      /*try {
        const response = await axios.post('http://localhost:8080/api/cyusers', { posted_data: 'example' });
        console.log('Returned data:', response);
      } catch (e) {
        console.log(`Axios request failed: ${e}`);
      }*/
      navigate('/question', { state: { filiere, ue, matiere, chapitre, nombreQuestions } });
    } else {
      alert('Veuillez remplir tous les champs.');
    }
  };

  return (
    <div className="revision-container">
      <h1 className="h1-revision">Révision</h1>
      <form className="form-revision" onSubmit={handleStartRevision}>
        <div className="revision-field-container">
          <label className="label-revision" htmlFor="filiere">
            Filière :
          </label>
          <select className="select-revision" name="filiere" value={filiere} onChange={(e) => setFiliere(e.target.value)}>
            <option value="">Choisir une filière</option>
            {filieres.map((f, index) => (
              <option key={index} value={f}>{f}</option>
            ))}
          </select>
        
          <label className="label-revision">
            UE : 
          </label>
          <select className="select-revision" value={ue} onChange={(e) => setUe(e.target.value)} disabled={!filiere}>
            <option value="">Choisir une UE</option>
            {filiere && ues[filiere]?.map((u, index) => (
              <option key={index} value={u}>{u}</option>
            ))}
          </select>
        

          <label className="label-revision">
            Matière:
          </label>
          <select className="select-revision" value={matiere} onChange={(e) => setMatiere(e.target.value)} disabled={!ue}>
            <option value="">Choisir une matière</option>
            {ue && matieresOptions.map((m, index) => (
              <option key={index} value={m}>{m}</option>
            ))}
          </select>
          
          <label className="label-revision">
            Chapitre:
          </label>
          <select className="select-revision" value={chapitre} onChange={(e) => setChapitre(e.target.value)} disabled={!matiere}>
            <option value="">Choisir un chapitre</option>
            {matiere && chapitresOptions.map((c, index) => (
              <option key={index} value={c}>{c}</option>
            ))}
          </select>
          
          <label className="label-revision">
            Nombre de questions:
          </label>
          <input className="input-revision" type="number" value={nombreQuestions} onChange={(e) => setNombreQuestions(e.target.value)} min="1" />
          
        </div>
        <button className="button-revision" type="submit">Commencer la révision</button>
      </form>
    </div>
  );
}

export default Revision;