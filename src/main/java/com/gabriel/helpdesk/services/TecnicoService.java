package com.gabriel.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.helpdesk.domain.Pessoa;
import com.gabriel.helpdesk.domain.Tecnico;
import com.gabriel.helpdesk.domain.dtos.TecnicoDTO;
import com.gabriel.helpdesk.repositores.PessoaRepository;
import com.gabriel.helpdesk.repositores.TecnicoRepository;
import com.gabriel.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.gabriel.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {
	
	@Autowired
	private TecnicoRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> oTecnico = repository.findById(id);
		
		return oTecnico.orElseThrow(() -> new ObjectNotFoundException("Técnico não encontrado! ID: " + id));	
	}

	public List<Tecnico> findAll() {		
		return repository.findAll();
	}

	public Tecnico create(TecnicoDTO oTecnicoDTO) {
		oTecnicoDTO.setId(null);
		ValidaCPFEmail(oTecnicoDTO);
		Tecnico oTecnico = new Tecnico(oTecnicoDTO);
		return repository.save(oTecnico);
	}
	
	public Tecnico update(Integer id, @Valid TecnicoDTO oTecnicoDTO) {
		oTecnicoDTO.setId(id);
		
		Tecnico oldObjTecnico = findById(id);
		
		ValidaCPFEmail(oTecnicoDTO);
		
		oldObjTecnico = new Tecnico(oTecnicoDTO);
		
		return repository.save(oldObjTecnico);
	}
	
	public void delete(Integer id) {
		Tecnico oTecnico = findById(id);
		
		if(oTecnico.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Técnico possui um serviço, não é possível exclui-lo!");
		}
		
		repository.deleteById(id);
	}

	private void ValidaCPFEmail(TecnicoDTO oTecnicoDTO) {
		Optional<Pessoa> oPessoa = pessoaRepository.findByCpf(oTecnicoDTO.getCpf());
		if(oPessoa.isPresent() && oPessoa.get().getId() != oTecnicoDTO.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
		}
		
		oPessoa = pessoaRepository.findByEmail(oTecnicoDTO.getEmail());
		if(oPessoa.isPresent() && oPessoa.get().getId() != oTecnicoDTO.getId()) {
			throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
		}
	}

}
