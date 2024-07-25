import React, { useState, useContext } from 'react';
import axios from 'axios';
import { AuthContext } from '../components/AuthContext';
import './UtilisateurDetails.css';

const utilisateurDetails = 
{
  id: 1,
  nom: 'Robert',
  prenom: 'Jean',
  dateCreation: '2023-07-10',
  nbQuestion: '5',
  nbQuestionApprouvees: '3',
  certif: 'Math'
}

const adminCertifs = ['Math', 'Physique']


function UtilisateurDetail() {

  const { isRootAdmin } = useContext(AuthContext);
  const [certif, setCertif] = useState(utilisateurDetails.certif);

  /*useEffect(() => {
    async function GetData() { /// we probably need to use useLocation and get the id of the question we are searching with last page
      var response = await axios.get(`http://localhost:8080/api/cyusers/`+ idQuestion);
      setData(response.data)
    }
    GetData()
  }, [])*/
  
  const handleCertif = () => {
    alert('Utilisateur certifié');
    /*
    axios.patch(certifier)
    */
  };

  const handleSupprimer = () => {
    alert('Utilisateur supprimé');
    /*
      axios.delete(supprimer)
    */
  };

  return (
    <div className="utilisateur-details-container">
      <h2>Détail de la Utilisateur</h2>
      <form>
        <p><strong>Nom:</strong> {utilisateurDetails.nom}</p>
        <p><strong>Prénom:</strong> {utilisateurDetails.prenom}</p>
        <p><strong>Certif:</strong> {utilisateurDetails.certif}</p>
        <p><strong>Nombre de questions :</strong> {utilisateurDetails.nbQuestion}</p>
        <p><strong>Nombre de questions approuvées:</strong> {utilisateurDetails.nbQuestionApprouvees}</p>
        <p><strong>Date de création:</strong> {utilisateurDetails.dateCreation}</p>
        <div className="buttons">
          <select value={certif} onChange={(e) => setCertif(e.target.value)}>
            <option value="">Choisir un certif</option>
            {adminCertifs.map((c, index) => (
              <option key={index} value={c}>{c}</option>
            ))}
          </select>
          {isRootAdmin && <button className="supprimer-certif" type="button" onClick={handleSupprimer}>Supprimer</button>}
          <button type="button" className="certifier" onClick={handleCertif}>Certifier</button>
          </div>
      </form>
    </div>
  );
}

export default UtilisateurDetail;
