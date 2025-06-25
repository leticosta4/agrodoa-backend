package com.labweb.agrodoa_backend.repository;

import com.labweb.agrodoa_backend.model.Denuncia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface DenunciaRepository extends JpaRepository<Denuncia, Long> {
    
}
