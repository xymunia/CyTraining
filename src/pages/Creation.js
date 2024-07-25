import React, { useState, useContext } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { AuthContext } from '../components/AuthContext';
import './Creation.css';

function Creation() {
  const [data, setData] = useState([]);
  const [nom, setNom] = useState('');
  const [prenom, setPrenom] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [filiere, setFiliere] = useState('');
  const [errorMessage, setErrorMessage] = useState('');
  const navigate = useNavigate();
  const { login } = useContext(AuthContext);

  // Get les différentes filière possibles
  /*useEffect(() => {
    async function GetData() {
      var response = await axios.get(`http://localhost:8080/api/cyusers/`+ idUser);
      setData(response.data)
    }
    GetData()
  }, [])*/

  const handleSubmit = (event) => {
    event.preventDefault();

    if (!nom || !prenom || !email || !password || !confirmPassword || !filiere) {
      setErrorMessage('Tous les champs doivent être remplis');
      return;
    }

    if (password !== confirmPassword) {
      setErrorMessage('Les mots de passe ne correspondent pas');
      return;
    }

    // envoyer les données
    /*
    try {                             ///////////////////////// faut faire le body correctement // TODO : body
      const response = await axios.post('http://localhost:8080/api/cyusers', { posted_data: 'example' });
      console.log('Returned data:', response);
    } catch (e) {
      console.log(`Axios request failed: ${e}`);
    }
    */
    

    // Redirige vers la page Menu.js si la création de compte est réussie
    // login(data.id de l'utilisateur créé)
    login();
    navigate('/menu');
  };

  return (
    <div className="creation-container">
      <h1 className="h1-creation">Création de Compte</h1>
      <form className="form-creation" onSubmit={handleSubmit}>
        <div className="creation-field-container">
          <label className="label-creation" htmlFor="nom">
            Nom:
          </label>
          <input
            className="input-creation"
            type="text"
            name="nom"
            value={nom}
            onChange={(e) => setNom(e.target.value)}
          />
          <label className="label-creation" htmlFor="prenom">
            Prénom:
          </label>
          <input
            className="input-creation"
            type="text"
            name="prenom"
            value={prenom}
            onChange={(e) => setPrenom(e.target.value)}
          />
          <label className="label-creation" htmlFor="email">
            Email:
          </label>
          <input
            className="input-creation"
            type="email"
            name="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
          <label className="label-creation" htmlFor="password">
            Mot de passe:
          </label>
          <input
            className="input-creation"
            type="password"
            name="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
          <label className="label-creation" htmlFor="confirmPassword">
            Confirmer le mot de passe:
          </label>
          <input
            className="input-creation"
            type="password"
            name="confirmPassword"
            value={confirmPassword}
            onChange={(e) => setConfirmPassword(e.target.value)}
          />
          <label className="label-creation" htmlFor="filiere">
            Filière:
          </label>
          <select
            name="filiere"
            value={filiere}
            onChange={(e) => setFiliere(e.target.value)}>

            <option value="">Sélectionnez une filière</option>
            <option value="informatique">Informatique</option>
            <option value="mathematiques">Mathématiques</option>
            <option value="physique">Physique</option>
            <option value="chimie">Chimie</option>

            {/*
              {data.map((filiere, index) => (
                <option key={index} value={filiere}>{filiere}</option>
              ))}
             */}
          </select>
        </div>
        <button type="submit" className="creation-button">Créer le compte</button>
        {errorMessage && <p className="error">{errorMessage}</p>}
      </form>
    </div>
  );
}

export default Creation;
