package br.com.gregoriohd.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gregoriohd.model.Cliente;
import br.com.gregoriohd.model.Endereco;
import br.com.gregoriohd.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoService enderecoService;
	
	public List<Cliente> todosClienes(){
		return clienteRepository.findAll();
	}
	
	public void salvarCliente(Cliente cliente) {
		clienteRepository.save(cliente);		
	}
	
	public Optional<Cliente> encontrarClientePorId(Integer clienteId) {
		 return clienteRepository.findById(clienteId);
	}

	public List<Endereco> encontarEnderecoPorClienteId(Integer clienteId){
		List<Endereco> listaEnderecosCliente = new ArrayList<>();
		for(Endereco endereco : enderecoService.todosEnderecos()) {
			if(endereco.getCliente().getId() == clienteId) {
				listaEnderecosCliente.add(endereco);
			}
		}
		
		return listaEnderecosCliente;
	}
}
