import React, { useContext } from 'react';
import { Link, useLocation } from 'react-router-dom';
import { AuthContext } from '../components/AuthContext';
import './Menu.css';

function Menu() {
  const location = useLocation();
  const { isAdmin, isRootAdmin } = useContext(AuthContext);
  const message = location.state?.message;

  return (
    <div className="menu-container">
      <h1 className="h1-menu">Menu</h1>
      {message && <div className="confirmation-message">{message}</div>}
      <div className="button-row">
        <Link to="/creation-question"><button className="large-button">
          Créer une Question
        </button></Link>
        <Link to="/revision"><button className="large-button">
          Réviser une Matière
        </button></Link>
      </div>
      {isAdmin &&
      <div className="button-row">
        <Link to="/questionlist">
          <button className="small-button">
            Approuver une Question
          </button>
        </Link>
        <Link to="/utilisateurlist">
          <button className="small-button">
            Certifier {isRootAdmin && 'ou Supprimer'} un Utilisateur
          </button>
        </Link>
        <Link to="/questionapprouveeslist">
          <button className="small-button">
            Supprimer une Question
          </button>
        </Link>
      </div>}
    </div>
  );
}

export default Menu;