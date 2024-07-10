import Home from "./pages/home"
import './App.css'
import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';


function App() {

  return (
    <Router>
    {/* Define as rotas usando o componente Routes */}
    <Routes>
      {/* Rota para a página Home com o componente associado */}
      <Route path="/" element={<Home />} />        
      <Route path="/login" element={<Login />} />
      <Route path="/Cadastro" element={<Cadastro />} />
    </Routes>
  </Router>

  )
}

export default App
