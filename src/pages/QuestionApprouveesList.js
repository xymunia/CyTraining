import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import './QuestionList.css';

const questions = [
  {
    id: 1,
    matiere: 'Mathématiques',
    chapitre: 'Algèbre',
    utilisateur: 'John Doe',
    dateCreation: '2023-07-10',
    question: 'Quelle est la solution de l\'équation x^2 - 4 = 0?'
  },
  {
    id: 2,
    matiere: 'Physique',
    chapitre: 'Mécanique',
    utilisateur: 'Jane Smith',
    dateCreation: '2023-07-11',
    question: 'Expliquez la deuxième loi de Newton.'
  },
  {
    id: 3,
    matiere: 'Physique',
    chapitre: 'Mécanique',
    utilisateur: 'Jane Smith',
    dateCreation: '2023-07-11',
    question: 'Expliquez la deuxième loi de mais de manière à ne pas omettre une étape de la démonstration associé, vous choisirez la démonstration de votre choix tout en expliquant ce choix de manière concise et détaillée, mais surtout, ne faites pas trop long, pas comme cette question que je fais plus long dans le but de i know i know i know iknow.'
  },
  {
    id: 4,
    matiere: 'Physique',
    chapitre: 'Mécanique',
    utilisateur: 'Jane Smith',
    dateCreation: '2023-07-11',
    question: 'Expliquez la deuxième loi de mais de manière à ne pas omettre une étape de la démonstration associé, vous choisirez la démonstration de votre choix tout en expliquant ce choix de manière concise et détaillée, mais surtout, ne faites pas trop long, pas comme cette question que je fais plus long dans le but de i know i know i know iknow.'
  },
  {
    id: 5,
    matiere: 'Physique',
    chapitre: 'Méczzanique',
    utilisateur: 'Jane Smith',
    dateCreation: '2023-07-11',
    question: 'Expliquez la deuxième loi de mais de manière à ne pas omettre une étape de la démonstration associé, vous choisirez la démonstration de votre choix tout en expliquant ce choix de manière concise et détaillée, mais surtout, ne faites pas trop long, pas comme cette question que je fais plus long dans le but de i know i know i know iknow.'
  }
];

function truncateText(text, maxLength) {
  if (text.length <= maxLength) {
    return text;
  }
  return text.substring(0, maxLength) + '...';
}

function QuestionList() {
  const [data, setData] = useState([]);
  const [selectedMatiere, setSelectedMatiere] = useState('');
  const [selectedChapitre, setSelectedChapitre] = useState('');
  const navigate = useNavigate();

  /*const handleViewDetails = (id) => {
    navigate(`/questiondetails/${id}`);
  };*/
  const handleViewDetails = () => {
    navigate(`/questionapprouveesdetails`);
  };

  const handleMatiereChange = (e) => {
    setSelectedMatiere(e.target.value);
    setSelectedChapitre('');
  };

  useEffect(() => {
    async function GetData() {
        var response = await axios.get(`http://localhost:8080/api/questions`);
        setData(response.data)
    }
    GetData()
  }, [])

  const filteredQuestions = questions.filter(q => 
    (selectedMatiere === '' || q.matiere === selectedMatiere) &&
    (selectedChapitre === '' || q.chapitre === selectedChapitre)
  );

  /*const filteredQuestions = data.filter(q => 
    (selectedMatiere === '' || q.matiere === selectedMatiere) &&
    (selectedChapitre === '' || q.chapitre === selectedChapitre)
  );*/

  const matieres = [...new Set(questions.map(q => q.matiere))];
  const chapitres = selectedMatiere 
    ? [...new Set(questions.filter(q => q.matiere === selectedMatiere).map(q => q.chapitre))]
    : [...new Set(questions.map(q => q.chapitre))];

  return (
    <div className="question-list-container">
      <h2>Approuver une Question</h2>
      <div className="question-list-filters">
        <select 
          value={selectedMatiere}
          onChange={handleMatiereChange}
        >
          <option value="">Toutes les matières</option>
          {matieres.map((matiere, index) => (
            <option key={index} value={matiere}>{matiere}</option>
          ))}
        </select>
        <select 
          value={selectedChapitre}
          onChange={(e) => setSelectedChapitre(e.target.value)}
        >
          <option value="">Tous les chapitres</option>
          {chapitres.map((chapitre, index) => (
            <option key={index} value={chapitre}>{chapitre}</option>
          ))}
        </select>
      </div>
      <ul className="question-list">
        {filteredQuestions.map((q) => (
          <li key={q.id} className="question-item">
            <div className="question-details">
              <h3>{truncateText(q.question, 90)}</h3>
              <p><strong>Matière:</strong> {truncateText(q.matiere, 20)}</p>
              <p><strong>Chapitre:</strong> {truncateText(q.chapitre, 20)}</p>
            </div>
            <div className="question-metadata">
              <p><strong>Utilisateur:</strong> {truncateText(q.utilisateur, 20)}</p>
              <p><strong>Date de création:</strong> {q.dateCreation}</p>
            </div>
            <button type="button" onClick={() => handleViewDetails(q.id)}>Voir les détails</button>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default QuestionList;
