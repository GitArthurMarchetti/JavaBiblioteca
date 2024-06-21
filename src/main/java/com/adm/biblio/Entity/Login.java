package com.adm.biblio.Entity;

public class Login {
    
    private Long matricula;
    private String senha;
    
    //--------------------------------------------------------------------------
    
    public Long getMatricula(){
        return matricula;
    }
    
     public String getSenha(){
        return senha;
    }

    public void setMatricula(Long matricula) {
        this.matricula = matricula;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
}
