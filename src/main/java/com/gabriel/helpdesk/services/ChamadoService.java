package com.gabriel.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.helpdesk.domain.Chamado;
import com.gabriel.helpdesk.domain.Cliente;
import com.gabriel.helpdesk.domain.Tecnico;
import com.gabriel.helpdesk.domain.dtos.ChamadoDTO;
import com.gabriel.helpdesk.domain.enums.Prioridade;
import com.gabriel.helpdesk.domain.enums.Status;
import com.gabriel.helpdesk.repositores.ChamadoRepository;
import com.gabriel.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class ChamadoService {
	
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	@Autowired 
	private TecnicoService tecnicoService;
	
	@Autowired
	private ClienteService clienteService;
	
	public Chamado findById(Integer id) {
		Optional<Chamado> oChamado = chamadoRepository.findById(id);
		
		return oChamado.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id));
	}
	
	public List<Chamado> findAll() {
		return chamadoRepository.findAll();
	}

	public Chamado create(@Valid ChamadoDTO oChamadoDTO) {
		return  chamadoRepository.save(newChamado(oChamadoDTO));
	}
	
	private Chamado newChamado(ChamadoDTO pChamadoDTO) {
		Tecnico oTecnico = tecnicoService.findById(pChamadoDTO.getTecnico());
		Cliente oCliente = clienteService.findById(pChamadoDTO.getCliente());
		
		Chamado oChamado = new Chamado();
		
		if(pChamadoDTO.getId() != null) {
			oChamado.setId(pChamadoDTO.getId());
		}
		
		oChamado.setTecnico(oTecnico);
		oChamado.setCliente(oCliente);
		oChamado.setPrioridade(Prioridade.toEnum(pChamadoDTO.getPrioridade()));
		oChamado.setStatus(Status.toEnum(pChamadoDTO.getStatus()));
		oChamado.setTitulo(pChamadoDTO.getTitulo());
		oChamado.setObservacao(pChamadoDTO.getObservacao());
		
		return oChamado;
	}

}
