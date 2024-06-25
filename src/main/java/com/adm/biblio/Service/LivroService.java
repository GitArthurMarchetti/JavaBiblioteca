
package com.adm.biblio.Service;

import com.adm.biblio.Entity.Livro;
import com.adm.biblio.Repository.LivroRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LivroService {
    
    @Autowired
    private LivroRepository livroRepository;
    
    public Long incluirLivro(Livro livro){
        if(livro.getTitulo()== null ||
                livro.getAutor()== null ||
                livro.getAno()== null){
            return null; 
        }
        return livroRepository.save(livro).getIdLivro();
    }
    
    
     public boolean excluirLivro(Long IdLivro){
      if(livroRepository.findById(IdLivro).isPresent()){
          livroRepository.deleteById(IdLivro);
          return true;
      }
        return false;
    }

     public Livro consultarLivroPorId(Long IdLivro){
         Optional<Livro> livro = livroRepository.findById(IdLivro);
         if(livro.isPresent()){
             return livro.get();
         }
         return null;
         
     }
     
     public List<Livro> listarLivro(){
         return livroRepository.findAll();
     }
     
     
     public Livro consultarLivroPorTitulo(String tituloLivro){
         return livroRepository.findByTitulo(tituloLivro.replaceAll("_", " "));
     }
     public boolean alterarLivro(Livro livro){
        if(livro.getTitulo() == null ||
            livro.getAutor() == null ||
            livro.getAno() == null ||
            livro.getEditora() == null )
        {
            return false; 
        }
        
        Livro livroDB= livroRepository.getReferenceById(livro.getIdLivro());
         if(livroDB != null){
             livroDB.setTitulo(livro.getTitulo());
             livroDB.setAutor(livro.getAutor());
             livroDB.setAno(livro.getAno());
             livroDB.setEditora(livro.getEditora());
             
             livroRepository.save(livroDB);
             return true;
         }
         return false;
     
     
}
}
