package com.example.ordermServico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ordermServico.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
}
