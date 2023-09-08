package com.sintad.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sintad.backend.models.TipoDocumento;


public interface ITipoDocumentoRepository extends JpaRepository<TipoDocumento, Long> {

    
}
