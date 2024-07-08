
package com.adm.biblio.Controller;

import com.adm.biblio.Entity.Estudante;
import com.adm.biblio.Entity.Login;
import com.adm.biblio.Service.EstudanteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
       @Operation(summary = "Manter estudantes no Sistema",description = "Listar Todos os estudantes cadastrados")
@ApiResponses(value = {
@ApiResponse(responseCode = "200", description = "Retorna a lista de estudantes" ),
@ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
@ApiResponse(responseCode = "500", description = "Erro interno no servidor!"),
})
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
        if(estudanteService.excluirEstudantePorId(idEstudante)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
     @GetMapping("/estudante/id/{IdEstudante}")
    public ResponseEntity<Estudante> consultaEstudantePorId(@PathVariable("IdEstudante") Long IdEstudante){
        Estudante estude = estudanteService.consultaEstudantePorId(IdEstudante);
        if(estude != null){
            return new ResponseEntity<>(estude,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @GetMapping("/estudante/matricula/{matricula}")
    public ResponseEntity<List<Estudante>> consultaEstudantePorMatricula(@PathVariable("matricula") Long matricula){
        List<Estudante> estude = estudanteService.consultaEstudantePorMatricula(matricula);
        if( ! estude.isEmpty() ){
            return new ResponseEntity<>(estude,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @GetMapping("/estudante/email/{email}")
    public ResponseEntity<List<Estudante>> consultaEstudantePorEmail(@PathVariable("email") String email){
        List<Estudante> estude = estudanteService.consultaEstudantePorEmail(email);
        if( ! estude.isEmpty() ){
            return new ResponseEntity<>(estude,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @GetMapping("/estudante/nome/{nome}")
    public ResponseEntity<List<Estudante>> consultaEstudantePorNome(@PathVariable("nome") String nome){
        List<Estudante> estude = estudanteService.consultaEstudantePorNome(nome);
        if(estude != null){
            return new ResponseEntity<>(estude,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @GetMapping("/estudante/pag/{pagina}")
    public ResponseEntity<List<Estudante>> listarEstudantesPaginando(@PathVariable("pagina") Integer pagina){
        List<Estudante> estude = estudanteService.listarEstudante(pagina);
        if(estude != null){
            return new ResponseEntity<>(estude,HttpStatus.OK);
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
