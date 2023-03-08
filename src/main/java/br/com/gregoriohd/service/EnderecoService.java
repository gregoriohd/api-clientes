package br.com.gregoriohd.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gregoriohd.model.Endereco;
import br.com.gregoriohd.repository.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public List<Endereco> todosEnderecos(){
		return enderecoRepository.findAll();
	}
	
//	public void salvarEndereco(Endereco endereco) {
//		enderecoRepository.save(endereco);
//	}
	
	public Endereco salvarEndereco(Endereco endereco) {
		return enderecoRepository.save(endereco);
	}
	
	public Optional<Endereco> encontarEnderecoPorId(Integer enderecoId){
		return enderecoRepository.findById(enderecoId);
	}
	
	
}
