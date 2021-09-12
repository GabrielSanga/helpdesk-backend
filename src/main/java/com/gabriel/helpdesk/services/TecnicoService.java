package com.gabriel.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.helpdesk.domain.Tecnico;
import com.gabriel.helpdesk.domain.dtos.TecnicoDTO;
import com.gabriel.helpdesk.repositores.TecnicoRepository;
import com.gabriel.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {
	
	@Autowired
	private TecnicoRepository repository;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> oTecnico = repository.findById(id);
		
		return oTecnico.orElseThrow(() -> new ObjectNotFoundException("Técnico não encontrado! ID: " + id));	
	}

	public List<Tecnico> findAll() {		
		return repository.findAll();
	}

	public Tecnico create(TecnicoDTO oTecnicoDTO) {
		oTecnicoDTO.setId(null);
		Tecnico oTecnico = new Tecnico(oTecnicoDTO);
		return repository.save(oTecnico);
	}

}
