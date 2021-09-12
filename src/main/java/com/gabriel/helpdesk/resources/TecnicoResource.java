package com.gabriel.helpdesk.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
