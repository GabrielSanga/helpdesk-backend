package com.gabriel.helpdesk;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gabriel.helpdesk.domain.Chamado;
import com.gabriel.helpdesk.domain.Cliente;
import com.gabriel.helpdesk.domain.Tecnico;
import com.gabriel.helpdesk.domain.enums.Perfil;
import com.gabriel.helpdesk.domain.enums.Prioridade;
import com.gabriel.helpdesk.domain.enums.Status;
import com.gabriel.helpdesk.repositores.ChamadoRepository;
import com.gabriel.helpdesk.repositores.ClienteRepository;
import com.gabriel.helpdesk.repositores.TecnicoRepository;

@SpringBootApplication
public class HelpdeskApplication implements CommandLineRunner{
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private ChamadoRepository chamadoRepository;

	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Tecnico oTecnico = new Tecnico(null, "Gabriel Sanga", "657.389.900-80", "gabriel.sanga@gmail.com", "12340");
		oTecnico.addPerfil(Perfil.ADMIN);
		
		Cliente oCliente = new Cliente(null, "Pedro Rocha", "657.382.945-80", "pedro@gmail.com", "1234452");
		
		Chamado oChamado = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro Chamado.", oCliente, oTecnico);
	
		tecnicoRepository.saveAll(Arrays.asList(oTecnico));
		clienteRepository.saveAll(Arrays.asList(oCliente));
		chamadoRepository.saveAll(Arrays.asList(oChamado));
		
	}

}
