package veexi.fazfreestyle.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import veexi.fazfreestyle.api.entities.Pessoa;
import veexi.fazfreestyle.api.services.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;

	@GetMapping
	public ResponseEntity<List<Pessoa>> getAllPessoas() {
		List<Pessoa> pessoas = pessoaService.getAllPessoas();
		return new ResponseEntity<>(pessoas, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> getPessoaById(@PathVariable Long id) {
		Pessoa pessoa = pessoaService.getPessoaById(id);
		return new ResponseEntity<>(pessoa, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Pessoa> savePessoa(@RequestBody Pessoa pessoa) {
		Pessoa savedPessoa = pessoaService.savePessoa(pessoa);
		return new ResponseEntity<>(savedPessoa, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Pessoa> updatePessoa(@PathVariable Long id, @RequestBody Pessoa pessoa) {
		Pessoa updatedPessoa = pessoaService.updatePessoa(id, pessoa);
		return new ResponseEntity<>(updatedPessoa, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePessoa(@PathVariable Long id) {
		pessoaService.deletePessoa(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
