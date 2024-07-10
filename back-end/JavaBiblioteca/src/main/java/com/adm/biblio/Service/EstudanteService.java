package com.adm.biblio.Service;

import com.adm.biblio.Entity.Estudante;
import com.adm.biblio.Repository.EstudanteRepository;
import org.springframework.data.domain.Pageable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class EstudanteService {
   
    @Autowired
    EstudanteRepository estudanteRepository;
   
    @Value("${biblio.registros.pag}")
    private int regPaginas;
    
    private Pageable pageable;
    
    
    public Long incluirEstudante(Estudante estudante){
        
        if(estudante.getMatricula() == null || estudante.getNome() == null ||
           estudante.getSenha() == null || estudante.getEmail() == null){
            return null;
        }
        if(estudanteRepository.findByMatricula(estudante.getMatricula()).isPresent()){
            return null;
        }
        String senhaCod = hashSenha(estudante.getSenha());
        estudante.setSenha(senhaCod);
        return estudanteRepository.save(estudante).getIdEstudante();
    }
    
    public Estudante loginEstudante(Long matricula, String senha){
        
        String senhaHash = "";
        Optional<Estudante> estudanteBD = estudanteRepository.findByMatricula(matricula);
        if(estudanteBD.isPresent()){
            senhaHash = hashSenha(senha);
            String senhaBD = estudanteBD.get().getSenha();
            //System.out.println("senha do banco...: " + senhaBD);
            //System.out.println("senha do login...: " + senhaHash);
            if( senhaHash.equals(senhaBD) ){               
               return estudanteBD.get();
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
    
    public boolean alterarEstudante(Estudante estudante){
        
        if( estudante.getNome() == null || estudante.getMatricula() == null ||
            estudante.getEmail() == null || estudante.getSenha() == null){
            return false;
        }        
        Estudante estudBD = estudanteRepository.getReferenceById(estudante.getIdEstudante());
        if(estudBD != null){
            estudBD.setNome(estudante.getNome());
            estudBD.setEmail(estudante.getEmail());
            estudBD.setMatricula(estudante.getMatricula());
            estudBD.setTelefone(estudante.getTelefone());
            estudBD.setSenha(hashSenha(estudante.getSenha()));
            estudanteRepository.save(estudBD);
            return true;
        }
        return false;
    }
    
    public boolean excluirEstudantePorId(Long IdEstudante){
        
        if(estudanteRepository.findById(IdEstudante).isPresent()){
            estudanteRepository.deleteById(IdEstudante);
            return true;
        }
        return false;
    }
    
    public boolean excluirEstudantePorMatricula(Long matricula){
        if(estudanteRepository.findByMatricula(matricula).isPresent()){
            estudanteRepository.deleteByMatricula(matricula);
            return true;
        }
        return false;
    }
    
    public Estudante consultaEstudantePorId(Long IdEstudante){
        return estudanteRepository.findById(IdEstudante).get();
    }
    
    public List<Estudante> consultaEstudantePorMatricula(Long matricula){
        List<Estudante> lista_e = new ArrayList();
        lista_e.add(estudanteRepository.findByMatricula(matricula).get());
        return lista_e;
    }
    
    public List<Estudante> consultaEstudantePorEmail(String email){
        List<Estudante> lista_e = new ArrayList();
        lista_e.add(estudanteRepository.findByEmail(email).get());
        return lista_e;
    }
    
   public List<Estudante> consultaEstudantePorNome(String nome){
       
       if(nome == null || nome.equalsIgnoreCase("")) { return null; }
       return estudanteRepository.findByNome(nome.replaceAll("_", " "));
   }
   
   public List<Estudante> listarEstudante(Integer pagina){
       
       if(pagina == null || pagina == 0) { pagina = 1; } 
       pagina = (pagina -1);
       Pageable pagsort = PageRequest.of(pagina,regPaginas,Sort.by("nome").ascending());
       List<Estudante> lestud = estudanteRepository.findAll(pagsort).getContent();
       if(lestud.isEmpty()){
           return null;
       } else {
           return lestud;
       }
   }
   
}