package br.com.softplan.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softplan.entity.Pessoa;
import br.com.softplan.repository.PessoaRepository;
import br.com.softplan.service.PessoaService;

@Service
public class PessoaServiceImpl implements PessoaService {

	private final PessoaRepository pessoaRepository;

	@Autowired
	public PessoaServiceImpl(PessoaRepository pessoaRepository) {
		this.pessoaRepository = pessoaRepository;
	}

	@Override
	public Iterable<Pessoa> findAll() {
		return pessoaRepository.findAll();
	}

	@Override
	public Pessoa save(Pessoa pessoa) {
		
		LocalDateTime localDateTime = LocalDateTime.now();
		
		if (pessoa.getId() == null) {
			pessoa.setDataCadastro(localDateTime);
		} else {
			pessoa.setDataAtualização(localDateTime);
		}
		
		return pessoaRepository.save(pessoa);
	}

	@Override
	public void update(Pessoa pessoa) {
		pessoaRepository.save(pessoa);
	}

	@Override
	public void updateForm(Pessoa pessoa) {
		Pessoa p = pessoaRepository.findById(pessoa.getId()).orElse(null);
		
		p.setNome(pessoa.getNome());
		p.setCpf(pessoa.getCpf());
		p.setEmail(pessoa.getEmail());
		
		LocalDateTime localDateTime = LocalDateTime.now();
		
		if (pessoa.getId() == null) {
			p.setDataCadastro(localDateTime);
		} else {
			p.setDataAtualização(localDateTime);
		}
		
		p.setDataNascimento(pessoa.getDataNascimento());
		p.setNacionalidade(pessoa.getNacionalidade());
		p.setNaturalidade(pessoa.getNaturalidade());
		
		p.setSexo(pessoa.getSexo());
		
		pessoaRepository.save(p);
	}

	@Override
	public void delete(Pessoa pessoa) {
		Pessoa e = pessoaRepository.findById(pessoa.getId()).orElse(null);

		pessoaRepository.delete(e);
	}

	@Override
	public Pessoa findById(Long id) {
		return pessoaRepository.findById(id).orElse(null);
	}
	
	@Override
	public Iterable<Pessoa> findByName(String nome) {
		return pessoaRepository.findByName(nome);
	}
	
	@Override
	public Iterable<Pessoa> findByCpf(String cpf) {
		return pessoaRepository.findByCpf(cpf);
	}

}
