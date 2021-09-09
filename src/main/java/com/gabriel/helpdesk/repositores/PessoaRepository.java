package com.gabriel.helpdesk.repositores;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabriel.helpdesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{

}
