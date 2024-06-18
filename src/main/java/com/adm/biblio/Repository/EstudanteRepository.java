package com.adm.biblio.Repository;

import com.adm.biblio.Entity.Estudante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EstudanteRepository extends JpaRepository<Estudante,Long>{
    
    Estudante findByMatricula(Long matricula);
    
}
