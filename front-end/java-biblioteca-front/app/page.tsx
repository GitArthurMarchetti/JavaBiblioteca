"use client"
import Image from "next/image";
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Cadastro from "./pages/cadastro"
import Login from "./pages/login"
import HomeBiblio from "./pages/home"

export default function Home() {
  return (
    <Router>
    <Routes>
      <Route path="/" element={<HomeBiblio />} />        
      <Route path="/login" element={<Login />} />
      <Route path="/Cadastro" element={<Cadastro />} />
    </Routes>
  </Router>
  );
}
