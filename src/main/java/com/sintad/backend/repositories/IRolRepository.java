package com.sintad.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sintad.backend.models.Rol;
import com.sintad.backend.utils.TypeRole;

public interface IRolRepository extends JpaRepository<Rol, Long> {
    public Optional<Rol> findByNombre(TypeRole nombre);
}