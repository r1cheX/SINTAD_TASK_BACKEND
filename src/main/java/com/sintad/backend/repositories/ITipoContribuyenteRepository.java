package com.sintad.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sintad.backend.models.TipoContribuyente;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ITipoContribuyenteRepository extends JpaRepository<TipoContribuyente, Long> {

    @Query("SELECT tc FROM TipoContribuyente tc LEFT JOIN FETCH tc.entidades")
    List<TipoContribuyente> findAllWithEntidades();
}
