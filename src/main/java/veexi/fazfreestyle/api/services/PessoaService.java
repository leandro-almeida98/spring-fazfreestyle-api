package veexi.fazfreestyle.api.services;

import java.util.List;
import java.util.NoSuchElementException; // Adicione esta importação

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import veexi.fazfreestyle.api.entities.Pessoa;
import veexi.fazfreestyle.api.exceptions.handle.NotFoundException;
import veexi.fazfreestyle.api.repositories.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	public List<Pessoa> findAll() {
		return pessoaRepository.findAll();
	}

	public Pessoa findById(Long id) {
		return pessoaRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Pessoa não encontrada"));
	}

	public Pessoa save(Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}

	public void deleteById(Long id) {
		pessoaRepository.deleteById(id);
	}

	public List<Pessoa> findByNome(String nome) {
		return pessoaRepository.findByNome(nome);
	}

	public List<Pessoa> findBySobrenome(String sobreNome) {
		return pessoaRepository.findBySobreNome(sobreNome);
	}

	public List<Pessoa> findByEmail(String email) {
		return pessoaRepository.findByEmail(email);
	}

	public List<Pessoa> getAllPessoas() {
		return pessoaRepository.findAll();
	}

	public Pessoa getPessoaById(Long id) {
		return pessoaRepository.findById(id).orElseThrow(() -> new NotFoundException("Pessoa não encontrada"));
	}

	public Pessoa savePessoa(Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}

	public Pessoa updatePessoa(Long id, Pessoa pessoa) {
		Pessoa pessoaToUpdate = getPessoaById(id);
		pessoaToUpdate.setNome(pessoa.getNome());
		pessoaToUpdate.setSobreNome(pessoa.getSobreNome());
		pessoaToUpdate.setEmail(pessoa.getEmail());
		pessoaToUpdate.setTelefone(pessoa.getTelefone());
		return pessoaRepository.save(pessoaToUpdate);
	}

	public void deletePessoa(Long id) {
		pessoaRepository.deleteById(id);
	}

	public boolean pessoaExists(Long id) {
		return pessoaRepository.existsById(id);
	}

}
