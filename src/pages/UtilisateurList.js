import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import './UtilisateurList.css';

const utilisateurs = [
  {
    id: 1,
    nom: 'Robert',
    prenom: 'Jean',
    filiere: 'Filiere A',
    certif: 'Math'
  },
  {
    id: 2,
    nom: 'Jean',
    prenom: 'Jacques',
    filiere: 'Filiere B',
    certif: 'Physique'
  }
];


function truncateText(text, maxLength) {
  if (text.length <= maxLength) {
    return text;
  }
  return text.substring(0, maxLength) + '...';
}

function UtilisateurList() {
  const [data, setData] = useState([]);
  const [selectedFiliere, setSelectedFiliere] = useState('');
  const [selectedMatiere, setSelectedMatiere] = useState('');
  const navigate = useNavigate();

  const handleViewDetails = (id) => {
    navigate(`/utilisateurdetails`);
  };

  const handleFiliereChange = (e) => {
    setSelectedFiliere(e.target.value);
  };

  const handleMatiereChange = (e) => {
    setSelectedMatiere(e.target.value);
  };

  useEffect(() => {
    async function GetData() {
        var response = await axios.get(`http://localhost:8080/api/cyusers`);
        setData(response.data)
    }
    GetData()
  }, [])
  console.log(data)

  const filteredUsers = utilisateurs.filter(u => 
    ((selectedMatiere === '' || u.certif === selectedMatiere) && (selectedFiliere === '' || u.filiere === selectedFiliere))
  );
  const matieres = [...new Set(utilisateurs.map(u => u.certif))];
  const filieres = [...new Set(utilisateurs.map(u => u.filiere))];

  // On filtre les utilisateurs si une matiere ou une filiere a été choisie
  /* const filteredUsers = data.filter(u => 
    ((selectedMatiere === '' || u.certif === selectedMatiere) && (selectedFiliere === '' || u.filiere === selectedFiliere))
  );
    code a tester quand api fonctionnera
  */


  return (
    <div className="utilisateur-list-container">
      <h2>Liste des Utilisateurs</h2>
      {/*data.map((ut, index) => (
            <p>{ut.nom}</p>
          ))
          /*data.nom
         */ }
      
      <div className="utilisateur-list-filters">
        <select 
            value={selectedFiliere}
            onChange={handleFiliereChange}
          >
          <option value="">Toutes les filiere</option>
          {filieres.map((filiere, index) => (
            <option key={index} value={filiere}>{filiere}</option>
          ))}
        </select>
        <select 
          value={selectedMatiere}
          onChange={handleMatiereChange}
        >
          <option value="">Toutes certifications</option>
          {matieres.map((matiere, index) => (
            <option key={index} value={matiere}>{matiere}</option>
          ))}
        </select>
      </div>

      <ul className="utilisateur-list">
        {filteredUsers.map((u) => (
          <li key={u.id} className="utilisateur-item">
            <div className="utilisateur-details">
              <p><strong>Nom:</strong> {u.nom}</p>
              <p><strong>Prénom:</strong> {u.prenom}</p>
            </div>
            <div className="utilisateur-metadata">
              <p><strong>Filière:</strong> {u.filiere}</p>
              <p><strong>Certifications:</strong> {truncateText(u.certif, 20)}</p>
            </div>
            <button type="button" onClick={() => handleViewDetails(u.id)}>Voir les détails</button>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default UtilisateurList;
