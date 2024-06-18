package com.adm.biblio.Service;

import com.adm.biblio.Repository.EstudanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstudanteService {
    
    @Autowired
    private EstudanteRepository estudanteRepository;
    
}
