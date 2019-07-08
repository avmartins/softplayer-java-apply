package br.com.softplan.rest;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.softplan.entity.Pessoa;
import br.com.softplan.exception.PessoaNotFoundException;
import br.com.softplan.service.PessoaService;

@RestController
@RequestMapping("/CadastroPessoas")
@CrossOrigin(origins = { "http://localhost:8090" })
public class CadastroPessoasRestController {

	private final PessoaService pessoaService;

	public CadastroPessoasRestController(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
	}

	// Cadastra Pessoa
	@PostMapping("/Cadastro")
	public ResponseEntity<Object> cadastro(@RequestBody Pessoa pessoa) {
		Pessoa savedPessoa = pessoaService.save(pessoa);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedPessoa.getId()).toUri();

		return ResponseEntity.created(location).build();

	}

	// Altera pessoa
	@PutMapping("/Alteracao/{id}")
	public ResponseEntity<Object> alteracao(@RequestBody Pessoa pessoa, @PathVariable long id) {

		Pessoa pessoaOptional = pessoaService.findById(id);

		if (pessoaOptional == null)
			return ResponseEntity.notFound().build();

		pessoa.setId(id);

		pessoaService.save(pessoa);

		return ResponseEntity.noContent().build();
	}

	// Remove pessoa
	@DeleteMapping("/Remocao/{id}")
	public void remocao(@PathVariable long id) {
		Pessoa pessoa = pessoaService.findById(id);
		pessoaService.delete(pessoa);
	}

	// Consulta todas as pessoas
	@GetMapping("/ConsultaTodos")
	public Iterable<Pessoa> consultaAll() {
		return pessoaService.findAll();
	}

	// Consulta Pessoa por nome
	@GetMapping("/ConsultaNome/{nome}")
	public Iterable<Pessoa> consultaNome(@PathVariable String nome) throws PessoaNotFoundException {

		Iterable<Pessoa> lista = null;

		if (nome != null && !nome.equals("")) {
			lista = pessoaService.findByName(nome.toUpperCase());
		}

		return lista;
	}

	// Consulta pessoa por cpf
	@GetMapping("/ConsultaCpf/{cpf}")
	public Iterable<Pessoa> consultaCpf(@PathVariable String cpf)
			throws PessoaNotFoundException {

		Iterable<Pessoa> lista = null;

		if (cpf != null && !cpf.equals("")) {
			lista = pessoaService.findByCpf(cpf);
		}

		return lista;
	}

	@GetMapping("/ConsultaId/{id}")
	public Pessoa consultaId(@PathVariable long id) throws PessoaNotFoundException {

		Pessoa pessoa = pessoaService.findById(id);

		return pessoa;
	}
	
	// Consulta Pessoas
	@GetMapping("/Consulta/{valor}/{campo}")
	public Iterable<Pessoa> consultaCpf(@PathVariable String valor, @PathVariable String campo)
			throws PessoaNotFoundException {

		Iterable<Pessoa> lista = null;
		
		if ( campo.equals("ID") ) {
			Pessoa pessoa = pessoaService.findById(new Long(valor));
			List<Pessoa> persons = new ArrayList<Pessoa>();    
			persons.add(pessoa);
			lista = persons; 
		} else if ( campo.equals("NOME") ) {
			lista = pessoaService.findByName(valor.toUpperCase());
		} else if ( campo.equals("CPF") ) {
			lista = pessoaService.findByCpf(valor);
		} else {
			lista = pessoaService.findAll();
		}
		
		return lista;
	}

}
