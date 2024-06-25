
package com.adm.biblio.Controller;

import com.adm.biblio.Entity.Estudante;
import com.adm.biblio.Entity.Login;
import com.adm.biblio.Service.EstudanteService;
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
public class EstudanteController {
    
    
     @Autowired
    private EstudanteService estudanteService;
    
    
      @PostMapping("/estudante")
    public ResponseEntity<Long> incluirNovoEstudante(@RequestBody Estudante estudante){
        Long idEs = estudanteService.incluirEstudante(estudante);
        if(idEs != null){
            return new ResponseEntity<>(idEs, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }//POST  http://localhost:8010/biblioteca/estudante
    
    
    @PostMapping("estudante/login")
    public ResponseEntity<Estudante> loginEstudante(@RequestBody Login login){
        Estudante estudante = estudanteService.loginEstudante(login.getMatricula(), login.getSenha());
        
        if(estudante != null){
            return new ResponseEntity<>(estudante, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
       
    @DeleteMapping("/{idEstudante}")
    public ResponseEntity<Long> excluirEstudante(@PathVariable("idEstudante") Long idEstudante){
        if(estudanteService.excluirEstudante(idEstudante)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
     @GetMapping("/estudante")
    public ResponseEntity<List<Estudante>> listarEstudantes(){
        List<Estudante> listEst = estudanteService.listarEstudantes();
        if(! listEst.isEmpty()){
            return new ResponseEntity<>(listEst ,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
      @GetMapping("/estudante/id/{IdEstudante}")
    public ResponseEntity<Estudante> consultaEstudantePorId(
    @PathVariable("IdCliente") Long IdEstudante){
        Estudante estudante = estudanteService.consultarEstudantePorId(IdEstudante);
        if(estudante != null){
            return new ResponseEntity<>(estudante ,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
     @PutMapping("cliente")
    public ResponseEntity<Boolean> alterarEstudante(@RequestBody Estudante estudante){
       if(estudanteService.alterarEstudante(estudante)){
           return new ResponseEntity<>(true, HttpStatus.OK);
       }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }
    
}
