package com.gabriel.helpdesk.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gabriel.helpdesk.domain.Chamado;
import com.gabriel.helpdesk.domain.dtos.ChamadoDTO;
import com.gabriel.helpdesk.services.ChamadoService;

@RestController
@RequestMapping(value = "chamados")
public class ChamadoResource {
	
	@Autowired
	private ChamadoService chamadoService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id){
		Chamado oChamado =chamadoService.findById(id); 
		
		return ResponseEntity.ok().body(new ChamadoDTO(oChamado));
	}
	
	@GetMapping
	public ResponseEntity<List<ChamadoDTO>> findAll(){
		List<Chamado> lstChamado = chamadoService.findAll();
		List<ChamadoDTO> lstChamadoDTO = lstChamado.stream().map(x -> new ChamadoDTO(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(lstChamadoDTO);
	}
	
	@PostMapping
	public ResponseEntity<ChamadoDTO> create(@Valid @RequestBody ChamadoDTO oChamadoDTO){
		Chamado oChamado = chamadoService.create(oChamadoDTO);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(oChamado.getId()).toUri();
		
		return ResponseEntity.created(uri).build();	
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ChamadoDTO> update(@Valid @RequestBody ChamadoDTO oChamadoDTO, @PathVariable Integer id){
		Chamado newChamado = chamadoService.update(oChamadoDTO, id);
		
		return ResponseEntity.ok().body(new ChamadoDTO(newChamado));
	}

}
