package com.gabriel.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.helpdesk.domain.Chamado;
import com.gabriel.helpdesk.domain.Cliente;
import com.gabriel.helpdesk.domain.Tecnico;
import com.gabriel.helpdesk.domain.enums.Perfil;
import com.gabriel.helpdesk.domain.enums.Prioridade;
import com.gabriel.helpdesk.domain.enums.Status;
import com.gabriel.helpdesk.repositores.ChamadoRepository;
import com.gabriel.helpdesk.repositores.ClienteRepository;
import com.gabriel.helpdesk.repositores.TecnicoRepository;

@Service
public class DBService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private TecnicoRepository tecnicoRepository;	
	@Autowired
	private ChamadoRepository chamadoRepository;

	public void instanciaDB() {
		Tecnico oTecnico = new Tecnico(null, "Gabriel Sanga", "657.389.900-80", "gabriel.sanga@gmail.com", "12340");
		oTecnico.addPerfil(Perfil.ADMIN);
		
		Tecnico oTecnico2 = new Tecnico(null, "Marcos Doub", "657.389.900-84", "marcos@gmail.com", "223430");
		
		Cliente oCliente = new Cliente(null, "Pedro Rocha", "657.382.945-80", "pedro@gmail.com", "1234452");
		
		Cliente oCliente2 = new Cliente(null, "Teste H2", "657.382.945-83", "teste@gmail.com", "4321");
		
		Chamado oChamado = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro Chamado.", oCliente, oTecnico);
	
		tecnicoRepository.saveAll(Arrays.asList(oTecnico, oTecnico2));
		clienteRepository.saveAll(Arrays.asList(oCliente, oCliente2));
		chamadoRepository.saveAll(Arrays.asList(oChamado));
	}

}
