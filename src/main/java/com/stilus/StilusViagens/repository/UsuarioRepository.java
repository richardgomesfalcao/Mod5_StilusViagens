package com.stilus.StilusViagens.repository;

import org.springframework.data.jpa.repository.JpaRepository;



import com.stilus.StilusViagens.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	
}
