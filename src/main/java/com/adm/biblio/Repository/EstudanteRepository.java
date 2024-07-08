package com.adm.biblio.Repository;

import com.adm.biblio.Entity.Estudante;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EstudanteRepository extends JpaRepository<Estudante,Long>{
    
    Optional<Estudante> findByEmail(String email);
    Optional<Estudante> findByMatricula(Long matricula);
    List<Estudante> findByNome(String nome);
    Page<Estudante> findAll(Pageable pagina);

    void deleteByMatricula(Long matricula);
}
