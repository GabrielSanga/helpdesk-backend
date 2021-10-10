package com.gabriel.helpdesk.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gabriel.helpdesk.domain.Cliente;
import com.gabriel.helpdesk.domain.dtos.ClienteDTO;
import com.gabriel.helpdesk.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id){
		
		Cliente oCliente = clienteService.findById(id);
		
		return ResponseEntity.ok().body(new ClienteDTO(oCliente));	
	}
	
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll(){
		List<Cliente> lstCliente = clienteService.findAll();
		List<ClienteDTO> lstClienteDTO = lstCliente.stream().map(x -> new ClienteDTO(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(lstClienteDTO);
	}
	
	@PostMapping
	public ResponseEntity<ClienteDTO> creacte(@Valid @RequestBody ClienteDTO oClienteDTO){
		Cliente oCliente = clienteService.create(oClienteDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(oCliente.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> update(@PathVariable Integer id,@Valid @RequestBody ClienteDTO oClienteDTO){		
		Cliente objCliente = clienteService.update(id, oClienteDTO);				
		return ResponseEntity.ok().body(new ClienteDTO(objCliente));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> delete(@PathVariable Integer id){
		clienteService.delete(id);
		
		return ResponseEntity.noContent().build();
	}

}
