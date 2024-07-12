"use client"
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './pages/home';
import Login from './pages/login';
import Cadastro from './pages/cadastro'; // Ensure Cadastro is imported correctly as defaul

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/cadastro" element={<Cadastro />} />
        <Route path="/login" element={<Login />} />
        {/* Other routes */}
      </Routes>
    </Router>
  );
}

export default App;
