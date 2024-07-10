"use client"
import Nav from "./component/nav"
import React, { useState } from 'react';
import axios from 'axios';

const cadastrar = async (nome: string, senha: string, email: string, nascimento: string, matricula: number, telefone: string) => {
  try {
    const response = await axios.post('http://localhost:8020/biblio/estudante', {
      nome: nome,
      matricula: matricula,
      nascimento: nascimento,
      email: email,
      senha: senha,
      telefone: telefone
    });
    alert("Cadastro realizado com sucesso!"); // Exibe mensagem de sucesso
    return response.data; // Retorna os dados da resposta
  } catch (error) {
    console.error('Erro ao cadastrar:', error);
    alert("Erro ao cadastrar usuário."); // Exibe mensagem de erro
    throw error; // Lança o erro para ser tratado no componente
  }
};

export default function Cadastro() {
  const [nome, setNome] = useState('');
  const [senha, setSenha] = useState('');
  const [email, setEmail] = useState('');
  const [nascimento, setNascimento] = useState('');
  const [matricula, setMatricula] = useState<number>(0); 
  const [telefone, setTelefone] = useState('');


  const handleCadastro = async () => {
    try {
      const response = await cadastrar(nome, senha, email, nascimento, matricula, telefone);
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
            Nome:
            <input
              type="text"
              value={nome}
              onChange={(e) => setNome(e.target.value)}
            />
          </label>
          <br />
          <label>
            Matricula:
            <input
              type="number"
              value={matricula}
              onChange={(e) => setMatricula(parseInt(e.target.value))}
            />
          </label>
          <br />
          <label>
            Senha:
            <input
              type="password"
              value={senha}
              onChange={(e) => setSenha(e.target.value)}
            />
          </label>
          <br />
          <label>
            Email:
            <input
              type="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
          </label>
          <br />
          <label>
          Nascimento:
            <input
              type="date"
              value={nascimento}
              onChange={(e) => setNascimento(e.target.value)}
            />
          </label>
          <br />
          <label>
          Telefone:
            <input
              type="text"
              value={telefone}
              onChange={(e) => setTelefone(e.target.value)}
            />
          </label>
          <br />
          <button type="button" onClick={handleCadastro}>
            Cadastrar
          </button>
        </form>
      </div>

    </>
  );
}