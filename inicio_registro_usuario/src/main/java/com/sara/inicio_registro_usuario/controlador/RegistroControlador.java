package com.sara.inicio_registro_usuario.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.sara.inicio_registro_usuario.servicio.UsuarioServicio;

@Controller
public class RegistroControlador {

    @Autowired
	private UsuarioServicio servicio;
	
	@GetMapping("/login")
	public String iniciarSesion() {
		return "login";
	}
    
}
