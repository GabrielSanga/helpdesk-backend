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

import com.gabriel.helpdesk.domain.Tecnico;
import com.gabriel.helpdesk.domain.dtos.TecnicoDTO;
import com.gabriel.helpdesk.services.TecnicoService;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {
	
	@Autowired
	private TecnicoService tecnicoService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id){
		
		Tecnico oTecnico = tecnicoService.findById(id);
		
		return ResponseEntity.ok().body(new TecnicoDTO(oTecnico));	
	}
	
	@GetMapping
	public ResponseEntity<List<TecnicoDTO>> findAll(){
		List<Tecnico> lstTecnico = tecnicoService.findAll();
		List<TecnicoDTO> lstTecnicoDTO = lstTecnico.stream().map(x -> new TecnicoDTO(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(lstTecnicoDTO);
	}
	
	@PostMapping
	public ResponseEntity<TecnicoDTO> creacte(@Valid @RequestBody TecnicoDTO oTecnicoDTO){
		Tecnico oTecnico = tecnicoService.create(oTecnicoDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(oTecnico.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id,@Valid @RequestBody TecnicoDTO oTecnicoDTO){		
		Tecnico objTecnico = tecnicoService.update(id, oTecnicoDTO);				
		return ResponseEntity.ok().body(new TecnicoDTO(objTecnico));
	}

}
