package com.sara.inicio_registro_usuario.servicio;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.sara.inicio_registro_usuario.dto.UsuarioRegistroDTO;
import com.sara.inicio_registro_usuario.modelo.Usuario;

public interface UsuarioServicio extends UserDetailsService{

    public Usuario guardar(UsuarioRegistroDTO registroDTO);

    public List<Usuario> listarUsuarios();
    
}
