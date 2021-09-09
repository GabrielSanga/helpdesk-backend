package com.gabriel.helpdesk.repositores;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabriel.helpdesk.domain.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer>{

}
