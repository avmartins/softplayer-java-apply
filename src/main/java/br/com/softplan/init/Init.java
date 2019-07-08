package br.com.softplan.init;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.softplan.entity.Pessoa;
import br.com.softplan.entity.User;
import br.com.softplan.entity.UserRole;
import br.com.softplan.repository.RegistroGeralRepository;
import br.com.softplan.service.PessoaService;
import br.com.softplan.service.UserService;

@SuppressWarnings("ALL")
@Component
public class Init implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	RegistroGeralRepository registroGeralRepository;

	private final UserService userService;
	private final PessoaService pessoaService;

	public Init(UserService userService,PessoaService pessoaService) {
		this.userService = userService;
		this.pessoaService = pessoaService;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {

		LocalDateTime localDateTime = LocalDateTime.now();
		int dia = 366;

		User root = new User("Administrador", "000.111.222-33", "root@email.com", "root", UserRole.ADMIN.getRole(),
				localDateTime.toLocalDate().minusDays(dia), true);
		userService.save(root);

		User user = new User("User", "111.555.333-22", "user@email.com", "user", UserRole.USER.getRole(),
				localDateTime.toLocalDate().minusDays(dia), true);
		userService.save(user);
		
		Pessoa pessoa1 = new Pessoa();
		pessoa1.setCpf("99999999999");
		pessoa1.setNome("Pessoa 1");
		
		pessoa1.setDataNascimento("1967-12-16");
		
		pessoa1.setEmail("a1@gmail.com");

		pessoa1.setNacionalidade("Brasileira");
		pessoa1.setNaturalidade("Rio de Janeiro");
		pessoa1.setSexo("M");
		pessoaService.save(pessoa1);
		
		Pessoa pessoa2 = new Pessoa();
		pessoa2.setCpf("88888888888");
		pessoa2.setNome("Pessoa 2");
		
		pessoa2.setDataNascimento("1957-11-17");
		
		pessoa2.setEmail("a2gmail.com");

		pessoa2.setNacionalidade("Brasileira");
		pessoa2.setNaturalidade("Rio de Janeiro");
		pessoa2.setSexo("M");
		pessoaService.save(pessoa2);
		
		Pessoa pessoa3 = new Pessoa();
		pessoa3.setCpf("77777777777");
		pessoa3.setNome("Pessoa 3");
		
		pessoa3.setDataNascimento("1947-09-10");
		
		pessoa3.setEmail("a3@gmail.com");

		pessoa3.setNacionalidade("Brasileira");
		pessoa3.setNaturalidade("Rio de Janeiro");
		pessoa3.setSexo("M");
		pessoaService.save(pessoa3);

	}
}
