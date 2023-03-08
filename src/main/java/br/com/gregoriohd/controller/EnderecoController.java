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
import br.com.gregoriohd.service.EnderecoService;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

	@Autowired
	private EnderecoService enderecoService;

	@Autowired
	private ClienteService clienteService;

	@GetMapping
	public ResponseEntity<List<Endereco>> todosEnderecos() {
		return new ResponseEntity<List<Endereco>>(enderecoService.todosEnderecos(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Endereco> salvarEndereco(@RequestBody Endereco endereco) {
		Endereco end = enderecoService.salvarEndereco(endereco);
		Cliente c = clienteService.encontrarClientePorId(end.getCliente().getId()).get();
		end.setCliente(c);
		return new ResponseEntity<Endereco>(end, HttpStatus.CREATED);
	}

	@GetMapping("{enderecoId}")
	public ResponseEntity<Endereco> encontarEnderecoPorId(@PathVariable Integer enderecoId) {
		return enderecoService.encontarEnderecoPorId(enderecoId).map(ResponseEntity::ok)
				.orElse(ResponseEntity.status(404).build());
	}

	
}
