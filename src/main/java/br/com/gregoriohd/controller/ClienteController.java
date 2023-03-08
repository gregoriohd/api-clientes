package br.com.gregoriohd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gregoriohd.model.Cliente;
import br.com.gregoriohd.model.Endereco;
import br.com.gregoriohd.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping
	public ResponseEntity<List<Cliente>> todosClientes() {
		return new ResponseEntity<>(clienteService.todosClienes(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Cliente> salvarClinete(@RequestBody Cliente cliente) {
		clienteService.salvarCliente(cliente);
		return new ResponseEntity<Cliente>(cliente, HttpStatus.CREATED);
	}

	@GetMapping("{clienteId}")
	public ResponseEntity<Cliente> encontrarClientePorId(@PathVariable Integer clienteId) {
		return clienteService.encontrarClientePorId(clienteId).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	@GetMapping("/{clienteId}/enderecos")
	public ResponseEntity<List<Endereco>> encontarEnderecoPorClienteId(@PathVariable Integer clienteId) {
		return new ResponseEntity<List<Endereco>>(clienteService.encontarEnderecoPorClienteId(clienteId),HttpStatus.OK);
	}
}
