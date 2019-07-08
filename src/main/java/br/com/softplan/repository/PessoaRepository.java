package  br.com.softplan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.softplan.entity.Pessoa;

/**
 * @author Anderson Virginio Martins
 */
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    @Query("select p from Pessoa p where p.nome like %?1% or p.cpf = ?2")
    Iterable<Pessoa> findByNameOrCpf(String nome,String cpf);
    
    @Query("select p from Pessoa p where UPPER(p.nome) like %?1%")
    Iterable<Pessoa> findByName(String nome);
    
    @Query("select p from Pessoa p where p.cpf = ?1")
    Iterable<Pessoa> findByCpf(String cpf);
    
}
