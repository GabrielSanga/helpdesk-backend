package com.gabriel.helpdesk.repositores;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabriel.helpdesk.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
