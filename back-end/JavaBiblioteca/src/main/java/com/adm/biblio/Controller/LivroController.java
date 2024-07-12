package com.adm.biblio.Controller;

import com.adm.biblio.Entity.Estudante;
import com.adm.biblio.Entity.Livro;
import com.adm.biblio.Service.LivroService;
import jakarta.websocket.server.PathParam;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LivroController {
    
    
    @Autowired
    public LivroService livroService;
    
    
    @PostMapping("/livro")
    public ResponseEntity<Long> incluirNovoLivro(@RequestBody Livro livro){
        Long idLivro = livroService.incluirLivro(livro);
        if(idLivro != null){
            return new ResponseEntity<>(idLivro, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }//POST  http://localhost:8010/biblioteca/estudante
   
       
    @DeleteMapping("/livro/{idLivro}")
    public ResponseEntity<Long> excluirLivro(@PathVariable("idLivro") Long idLivro){
        if(livroService.excluirLivro(idLivro)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    
     // (Get) http://localhost:8010/venda/produto/10
    @GetMapping("/Livro/id/{IdLivro}") 
    public ResponseEntity<Livro> consultaLivroPorId(@PathVariable("IdLivro")
                                                        Long IdLivro){
        Livro livro = livroService.consultarLivroPorId(IdLivro);
        if(livro != null){
            return new ResponseEntity<>(livro,HttpStatus.OK);
        }
        return new ResponseEntity<>(livro,HttpStatus.NOT_FOUND);
    }
    
    
    
    @GetMapping("/livro/titulo/{tituloLivro}") 
    public ResponseEntity<Livro> consultaLivroPorTitulo(@PathVariable("tituloLivro")
                                                        String tituloLivro){
        Livro livro = livroService.consultarLivroPorTitulo(tituloLivro);
        if(livro != null){
            return new ResponseEntity<>(livro,HttpStatus.OK);
        }
        return new ResponseEntity<>(livro,HttpStatus.NOT_FOUND);
    }
    
  
     @PutMapping("livro")
    public ResponseEntity<Boolean> alterarLivro(@RequestBody Livro livro){
       if(livroService.alterarLivro(livro)){
           return new ResponseEntity<>(true, HttpStatus.OK);
       }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }
    @GetMapping("/livro/pag/{pagina}")
    public ResponseEntity<List<Livro>> listarLivrosPorPaginas(@PathVariable("pagina") Integer pagina){
        System.out.println("Pagina: " + pagina);
        List<Livro> livros = livroService.listarLivro(pagina);
        if( ! livros.isEmpty() ){
            return new ResponseEntity<>(livros, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }//POST  http://localhost:8010/biblioteca/estudante
    
}
