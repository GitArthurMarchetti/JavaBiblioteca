package com.adm.biblio.Service;

import com.adm.biblio.Entity.Estudante;
import com.adm.biblio.Repository.EstudanteRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstudanteService {
   
    @Autowired
    EstudanteRepository estudanteRepository;
   
    public Long incluirEstudante(Estudante estudante){
       
        if(estudante.getMatricula() == null || estudante.getNome() == null ||
           estudante.getSenha() == null || estudante.getEmail() == null){
            return null;
        }
        if(estudanteRepository.findByMatricula(estudante.getMatricula()) != null){
            return null;
        }
        String senhaCod = hashSenha(estudante.getSenha());
        estudante.setSenha(senhaCod);        
        return estudanteRepository.save(estudante).getIdEstudante();
    }
   
    public Estudante loginEstudante(Long matricula, String senha){
       
        String senhaHash = "";
        Estudante estudanteBD = estudanteRepository.findByMatricula(matricula);
        if(estudanteBD != null){
            senhaHash = hashSenha(senha);
            String senhaBD = estudanteBD.getSenha();
            //System.out.println("senha do banco...: " + senhaBD);
            //System.out.println("senha do login...: " + senhaHash);
            if( senhaHash.equals(senhaBD) ){              
               return estudanteBD;
            }
        }        
        return null;
    }
   
    public String hashSenha(String passwd){
        String passwdCod = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            //md. update(salt);
            final byte[] hashBytes = md.digest(passwd.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < hashBytes.length; i++) {
                sb.append(Integer.toString((hashBytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            passwdCod = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.getLocalizedMessage();
        }
        return passwdCod;
    }
   
    public List<Estudante> listarEstudantes(){
       
        return estudanteRepository.findAll();      
    }
   
    public boolean excluirEstudante(Long IdEstudante){
        if (estudanteRepository.findById(IdEstudante).isPresent()){
            estudanteRepository.deleteById(IdEstudante);
            return true;
        }
        return false;
    }
   
    public Estudante consultarEstudantePorId(Long IdEstudante){
        Estudante estudante = estudanteRepository.findById(IdEstudante).get();
        if(estudante != null){
            return estudante;
        }
        return null;
    }
   
     public boolean alterarEstudante(Estudante estudante){
        
        if(estudante.getNome() == null || estudante.getNome() == ""){
        return false;
    }
        if(estudante.getEmail() == null || estudante.getEmail() == ""){
        return false;
    }
         if(estudante.getMatricula() == null){
            return false;
        }
         Estudante estBD = estudanteRepository.getReferenceById(estudante.getIdEstudante());
       
        if( estBD != null){
        if(estBD.getMatricula() != estudante.getMatricula() &&
            estudanteRepository.findByMatricula(estudante.getMatricula()) != null){    
            return false;
         }
        

        estBD.setMatricula(estudante.getMatricula());
        estBD.setEmail(estudante.getEmail());
        estBD.setSenha(estudante.getSenha());
        estBD.setTelefone(estudante.getTelefone());
        estBD.setNascimento(estudante.getNascimento());
        estBD.setNome(estudante.getNome());
        estudanteRepository.save(estBD);
        return true;
        }
        return false;
    }
    
}