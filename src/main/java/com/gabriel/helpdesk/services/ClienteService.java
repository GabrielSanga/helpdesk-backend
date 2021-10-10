package com.gabriel.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.helpdesk.domain.Pessoa;
import com.gabriel.helpdesk.domain.Cliente;
import com.gabriel.helpdesk.domain.dtos.ClienteDTO;
import com.gabriel.helpdesk.repositores.PessoaRepository;
import com.gabriel.helpdesk.repositores.ClienteRepository;
import com.gabriel.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.gabriel.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Cliente findById(Integer id) {
		Optional<Cliente> oCliente = repository.findById(id);
		
		return oCliente.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado! ID: " + id));	
	}

	public List<Cliente> findAll() {		
		return repository.findAll();
	}

	public Cliente create(ClienteDTO oClienteDTO) {
		oClienteDTO.setId(null);
		ValidaCPFEmail(oClienteDTO);
		Cliente oCliente = new Cliente(oClienteDTO);
		return repository.save(oCliente);
	}
	
	public Cliente update(Integer id, @Valid ClienteDTO oClienteDTO) {
		oClienteDTO.setId(id);
		
		Cliente oldObjCliente = findById(id);
		
		ValidaCPFEmail(oClienteDTO);
		
		oldObjCliente = new Cliente(oClienteDTO);
		
		return repository.save(oldObjCliente);
	}
	
	public void delete(Integer id) {
		Cliente oCliente = findById(id);
		
		if(oCliente.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Técnico possui um serviço, não é possível exclui-lo!");
		}
		
		repository.deleteById(id);
	}

	private void ValidaCPFEmail(ClienteDTO oClienteDTO) {
		Optional<Pessoa> oPessoa = pessoaRepository.findByCpf(oClienteDTO.getCpf());
		if(oPessoa.isPresent() && oPessoa.get().getId() != oClienteDTO.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
		}
		
		oPessoa = pessoaRepository.findByEmail(oClienteDTO.getEmail());
		if(oPessoa.isPresent() && oPessoa.get().getId() != oClienteDTO.getId()) {
			throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
		}
	}

}
