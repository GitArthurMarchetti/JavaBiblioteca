"use client"
import Nav from "./component/nav"
import React, { useState } from 'react';
import axios from 'axios';

const cadastrar = async (nome: string, senha: string, email: string, nascimento: string, matricula: number) => {
  try {
    const response = await axios.post('http://localhost:8020/biblio/estudante', {
      nome: nome,
      matricula: matricula,
      nascimento: nascimento,
      email: email,
      senha: senha,
    });
    return response.data;
  } catch (error) {
    throw error;
  }
};

export default function Cadastro() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const handleLogin = async () => {
    try {
      const response = await cadastrar(nome, senha, email, nascimento, matricula);
      alert(response);
    } catch (error) {
      console.error('Erro ao se logar:', error);
    }
  };
  return (
    <>
      <Nav />
      <div>
        <h1>Cadastrar usuario</h1>
        <form>
          <label>
            Usuário:
            <input
              type="text"
              value={nome}
              onChange={(e) => setUsername(e.target.value)}
            />
          </label>
          <br />
          <label>
            Senha:
            <input
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
          </label>
          <br />
          <button type="button" onClick={handleLogin}>
            Login
          </button>
        </form>
      </div>

    </>
  );
}