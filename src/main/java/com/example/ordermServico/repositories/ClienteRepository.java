package com.example.ordermServico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ordermServico.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
}
