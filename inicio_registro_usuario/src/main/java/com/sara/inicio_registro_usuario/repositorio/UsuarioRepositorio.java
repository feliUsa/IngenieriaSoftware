package com.sara.inicio_registro_usuario.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sara.inicio_registro_usuario.modelo.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{

    public Usuario findByEmail(String email);
    
}
