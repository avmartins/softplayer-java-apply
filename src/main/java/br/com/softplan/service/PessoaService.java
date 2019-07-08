package  br.com.softplan.service;

import  br.com.softplan.entity.Pessoa;

public interface PessoaService {

	Iterable<Pessoa> findAll();
	
	Iterable<Pessoa> findByName(String nome);
	
	Iterable<Pessoa> findByCpf(String cpf);
	
	Pessoa findById(Long id);

	Pessoa save(Pessoa Pessoa);

	void update(Pessoa Pessoa);

	void updateForm(Pessoa Pessoa);
	
	void delete(Pessoa Pessoa);

}
