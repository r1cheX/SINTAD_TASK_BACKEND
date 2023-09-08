package com.sintad.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sintad.backend.models.TipoContribuyente;

public interface ITipoContribuyenteRepository extends JpaRepository<TipoContribuyente, Long> {
}
