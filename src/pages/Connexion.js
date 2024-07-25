import React, { useState, useContext } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { AuthContext } from '../components/AuthContext';
import './Connexion.css';

function Connexion() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [errorMessage, setErrorMessage] = useState('');
  const navigate = useNavigate();
  const { login } = useContext(AuthContext);

  const handleSubmit = (event) => {
    event.preventDefault();
    /*
      async function GetData(email, password) {
        var response = await axios.get(`http://localhost:8080/api/cyusers/`+ email + password);
        setData(response.data)
      }
      GetData();
    */
    
    const validEmail = 'j@gmail.com';
    const validPassword = '1';
    
    //if (GetData(email, password)) {
    if (email === validEmail && password === validPassword) {
      login();
      // login(data.id)   pour faire le login correctement
      navigate('/menu');
    } else {
      setErrorMessage('Identifiant ou mot de passe incorrect');
    }
  };

  return (
    <div className="connexion-container">
      <h1 className="h1-connexion">Connexion</h1>
      <form className="connexion-form" onSubmit={handleSubmit}>
        <div className="connexion-field-container">
          <label className="connexion-label" htmlFor="email">
            Email :
          </label>
          <input
            className="connexion-input"
            type="email"
            name="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
       
          <label className="connexion-label" htmlFor="password">
            Mot de passe :
          </label>
          <input
            className="connexion-input"
            type="password"
            name="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>
        <button type="submit" className="connexion-button">Se connecter</button>
        {errorMessage && <p className="error">{errorMessage}</p>}
      </form>
    </div>
  );
}

export default Connexion;