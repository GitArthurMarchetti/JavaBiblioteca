"use client"
import Nav from "./component/nav"
import React, { useState } from 'react';
import axios from 'axios';

const logar = async (matricula: number, senha: string) => {
  try {
    const response = await axios.post('http://localhost:8020/biblio/estudante/login', {
      matricula: matricula,
      senha: senha,
    });
    alert("Login realizado com sucesso!"); // Exibe mensagem de sucesso
    return response.data; // Retorna os dados da resposta
  } catch (error) {
    console.error('Erro ao cadastrar:', error);
    alert("Erro ao cadastrar usuário."); // Exibe mensagem de erro
    throw error; // Lança o erro para ser tratado no componente
  }
};


export default function Login() {
  const [matricula, setMatricula] = useState<number>(0);
  const [senha, setSenha] = useState('');
  const handleLogin = async () => {
    try {
      const response = await logar(matricula, senha);
      alert(response);
    } catch (error) {
      console.error('Erro ao se logar:', error);
    }
  };

  return (
    <>
      <Nav />
      <h1>Login</h1>
      <form>
        <label>
          Matricula:
          <input
            type="text"
            value={matricula}
            onChange={(e) => setMatricula(parseInt(e.target.value))}
          />
        </label>
        <br />
        <label>
          Senha:  
          <input
            type="senha"
            value={senha}
            onChange={(e) => setSenha(e.target.value)}
          />
        </label>
        <br />
        <button type="button" onClick={handleLogin}>
          Logar
        </button>
      </form>

    </>
  );
}