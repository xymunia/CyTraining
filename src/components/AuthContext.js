import React, { createContext, useState, useEffect } from 'react';

const AuthContext = createContext();

const AuthProvider = ({ children }) => {
  const [isAuthenticated, setIsAuthenticated] = useState(localStorage.getItem("isAuthenticated") || false);
  //const [idUser, setIdUser] = useState(localStorage.getItem("idUser") || null);
  const [idUser, setIdUser] = useState(1);
  const [isAdmin, setIsAdmin] = useState(true);
  const [isRootAdmin, setIsRootAdmin] = useState(true);

  const login = () => { //(id) => {    quand on fera le login correctement
    setIsAuthenticated(true);
    localStorage.setItem('isAuthenticated', true);
    //setIdUser(id);
    //localStorage.setItem('idUser');
    // if is Admin, setIsAdmin(true);
    // if is RootAdmin, setIsRootAdmin(true);     faut faire les localStorage avec    ///////////////////////////////
  };

  const logout = () => {
    setIsAuthenticated(false);
    localStorage.removeItem('isAuthenticated');
    //setIdUser(null);
    //localStorage.removeItem('idUser');
  };

  return (
    <AuthContext.Provider value={{ isAuthenticated, isAdmin, idUser, setIdUser, setIsAdmin, isRootAdmin, setIsAdmin, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

export { AuthContext, AuthProvider };