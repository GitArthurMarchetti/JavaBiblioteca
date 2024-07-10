package com.adm.biblio.Repository;

import com.adm.biblio.Entity.Emprestimo;
import com.adm.biblio.Entity.Estudante;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long>{
    
  List<Emprestimo> findByEstudante(Estudante estudante); 
    
}
