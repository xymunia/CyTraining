import React, { useEffect, useState, useContext } from 'react';
import { AuthContext } from '../components/AuthContext';
import axios from 'axios';
import './Profil.css';

function Profil() {
  // Initial state with pre-filled values
  const [data, setData] = useState([]);
  const { idUser } = useContext(AuthContext);
  const [profile, setProfile] = useState({
    nom: 'Doe',
    prenom: 'John',
    email: 'john.doe@example.com',
    mdp: 'password123',
    filiere: 'Informatique'
  });

  // Handle change for input fields
  const handleChange = (e) => {
    const { name, value } = e.target;
    setProfile({
      ...profile,
      [name]: value
    });
  };

  // Handle form submission
  const handleSubmit = (e) => {
    e.preventDefault();
    // Here you would normally handle the form submission (e.g., send data to a server)
    console.log('Profile updated:', profile);
    /*
      axios.put(update)
      FAIRE UN PUT ICI
    */
  };

  /*useEffect(() => {
    document.documentElement.style.overflow = "hidden"; apparement ca enlève le scrolling mais c'est trop bizarre
  }, []);*/
  
  useEffect(() => {
    async function GetData() {
      var response = await axios.get(`http://localhost:8080/api/cyusers/`+ idUser);
      setData(response.data)
    }
    GetData()
  }, [])

  return (
    <div className="container">
      <h1>Profil</h1>
      <form onSubmit={handleSubmit}>
        <div className="field-profil">
          <label className="label-profil" htmlFor="nom">Nom</label>
          <input
            className="input-profil"
            type="text"
            id="nom"
            name="nom"
            value={profile.nom}
            onChange={handleChange}
          />
        </div>
        <div className="field-profil">
          <label className="label-profil" htmlFor="prenom">Prénom</label>
          <input
            className="input-profil"
            type="text"
            id="prenom"
            name="prenom"
            value={profile.prenom}
            onChange={handleChange}
          />
        </div>
        <div className="field-profil">
          <label className="label-profil" htmlFor="email">Email</label>
          <input
            className="input-profil"
            type="email"
            id="email"
            name="email"
            value={profile.email}
            onChange={handleChange}
          />
        </div>
        <div className="field-profil">
          <label className="label-profil" htmlFor="mdp">Mot de passe</label>
          <input
            className="input-profil"
            type="password"
            id="mdp"
            name="mdp"
            value={profile.mdp}
            onChange={handleChange}
          />
        </div>
        <div className="field-profil">
          <label className="label-profil" htmlFor="filiere">Filière</label>
          <input
            className="input-profil"
            type="text"
            id="filiere"
            name="filiere"
            value={profile.filiere}
            onChange={handleChange}
          />
        </div>
        <button type="submit" className="button-profil">Mettre à jour</button>
      </form>
    </div>
  );
}

export default Profil;