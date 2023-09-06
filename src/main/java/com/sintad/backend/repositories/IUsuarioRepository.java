package com.sintad.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sintad.backend.models.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

    public Optional<Usuario> findByEmail(String email);

    public Boolean existsByEmail(String email);

}
