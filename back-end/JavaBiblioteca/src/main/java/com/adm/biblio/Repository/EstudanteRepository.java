package com.adm.biblio.Repository;

import com.adm.biblio.Entity.Estudante;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EstudanteRepository extends JpaRepository<Estudante,Long>{
    
    Estudante findByMatricula(Long matricula);
    Estudante findByEmail(String email);
    List<Estudante> findByNome(String nome);
}
