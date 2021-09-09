package com.gabriel.helpdesk.repositores;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabriel.helpdesk.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer>{

}
