package com.sintad.backend.repositories;

import com.sintad.backend.models.Entidad;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IEntidadRepository extends JpaRepository<Entidad, Long>{
    
}
