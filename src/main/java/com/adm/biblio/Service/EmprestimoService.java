package com.adm.biblio.Service;

import com.adm.biblio.Entity.Emprestimo;
import com.adm.biblio.Entity.Estudante;
import com.adm.biblio.Repository.EmprestimoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmprestimoService {
    
    @Autowired
    private EmprestimoRepository emprestimoRepository;
    
    @Autowired
    private EstudanteService estudanteService;
    
    public Long incluirEmprestimo(Emprestimo emprestimo){
        
        if(emprestimo.getEstudante() == null ||
           emprestimo.getDataEmprestimo() == null ||
           emprestimo.getDataEntrega() == null ||
           emprestimo.getLivro() == null){
            return null;
        }
        
      return emprestimoRepository.save(emprestimo).getIdEmprestimo();  
    }
    
       public boolean excluirEmprestimo(Long IdEmprestimo){
        if(emprestimoRepository.existsById(IdEmprestimo)){
            emprestimoRepository.deleteById(IdEmprestimo);
            return true;
        }
        return false;
    }
       
       
        public Emprestimo consultaEmprestimoPorId(Long IdEmprestimo){
       return emprestimoRepository.findById(IdEmprestimo).get();
    }
       
       public List<Emprestimo> listarEmprestimoPorEstudante(Long IdEstudante){
       Estudante estudante = estudanteService.consultarEstudantePorId(IdEstudante);
        if(estudante != null){
            return emprestimoRepository.findByEstudante(estudante);
        }
        return null;
    }
       
       public boolean alterarEmprestimo(Emprestimo emprestimo){
         Emprestimo emprestimoBD = emprestimoRepository.getReferenceById(emprestimo.getIdEmprestimo());
        
        if(emprestimoBD != null){
            if(emprestimoBD.getMatricula()== null ||
               emprestimoBD.getDataEmprestimo()== null ||
               emprestimoBD.getDataEntrega()== null){
                return false;
            }
            emprestimoBD.setDataEmprestimo(emprestimo.getDataEmprestimo());
            emprestimoBD.setDataEntrega(emprestimo.getDataEntrega());
            emprestimoBD.setDevolucao(emprestimo.getDevolucao());
            emprestimoBD.setLivro(emprestimo.getLivro());
            emprestimoBD.setEstudante(emprestimo.getEstudante());
            emprestimoRepository.save(emprestimoBD);
            return true;
        }
        return false;
    }
       
}
