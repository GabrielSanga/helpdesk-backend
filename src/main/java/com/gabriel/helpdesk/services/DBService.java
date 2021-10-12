package com.gabriel.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	public void instanciaDB() {
		Tecnico oTecnico = new Tecnico(null, "Gabriel Sanga", "69802545023", "gabriel.sanga@gmail.com", encoder.encode("12340"));
		oTecnico.addPerfil(Perfil.ADMIN);
		
		Tecnico oTecnico2 = new Tecnico(null, "Marcos Doub", "06937420030", "marcos@gmail.com", encoder.encode("223430"));
		
		Cliente oCliente = new Cliente(null, "Pedro Rocha", "97841791054", "pedro@gmail.com", encoder.encode("1234452"));
		
		Cliente oCliente2 = new Cliente(null, "Teste H2", "00827598041", "teste@gmail.com", encoder.encode("4321"));
		
		Chamado oChamado = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro Chamado.", oCliente, oTecnico);
	
		tecnicoRepository.saveAll(Arrays.asList(oTecnico, oTecnico2));
		clienteRepository.saveAll(Arrays.asList(oCliente, oCliente2));
		chamadoRepository.saveAll(Arrays.asList(oChamado));
	}

}
