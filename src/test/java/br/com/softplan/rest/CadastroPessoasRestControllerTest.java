package br.com.softplan.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.URI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.softplan.Application;
import br.com.softplan.entity.Pessoa;

/**
 * @author Anderson Virginio Martins
 * 
 * Claase responsável por testar a CadastroPessoasRestController 
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
@ActiveProfiles(profiles = "test")
public class CadastroPessoasRestControllerTest {
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testConsultaNome() {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<Iterable<Pessoa>> response = restTemplate.exchange("http://localhost:8090/CadastroPessoas/ConsultaNome/Pessoa 1",
				HttpMethod.GET, null, new ParameterizedTypeReference<Iterable<Pessoa>>() {
				});

		Iterable<Pessoa> listaPessoaes = response.getBody();
		
		System.out.println("####################### testConsultaNome #######################");

		for (Pessoa pessoa : listaPessoaes) {
			System.out.println("Nome : "+pessoa.getNome());
		}

		assertNotNull(response.getBody());
	}
	
	@Test
	public void testConsultaCpf() {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<Iterable<Pessoa>> response = restTemplate.exchange("http://localhost:8090/CadastroPessoas/ConsultaCpf/77777777777",
				HttpMethod.GET, null, new ParameterizedTypeReference<Iterable<Pessoa>>() {
				});

		Iterable<Pessoa> listaPessoaes = response.getBody();
		
		System.out.println("\n####################### testConsultaCpf #######################");

		for (Pessoa pessoa : listaPessoaes) {
			System.out.println("Nome : "+pessoa.getNome());
		}

		assertNotNull(response.getBody());
	}

	@Test
	public void testCadastro() {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();

		Pessoa pessoa = new Pessoa();
		pessoa.setCpf("07225612700");
		pessoa.setNome("Pessoa 5 teste");
		
		pessoa.setDataNascimento("1967-12-16");
		
		pessoa.setEmail("ax@gmail.com");

		pessoa.setNacionalidade("Brasileira");
		pessoa.setNaturalidade("Rio de Janeiro");
		pessoa.setSexo("M");
		
		HttpEntity<Pessoa> request = new HttpEntity<>(pessoa, headers);

		final String baseUrl = "http://localhost:8090/CadastroPessoas/Cadastro";

		try {
			URI uri = new URI(baseUrl);
			restTemplate.postForEntity(uri, request, Pessoa.class);
			assertNotNull(request.getBody());
			System.out.println("####################### testCadastro #######################");
			System.out.println("Pessoa Criado com sucesso!");
		} catch (HttpClientErrorException ex) {
			assertEquals(400, ex.getRawStatusCode());
			assertEquals(true, ex.getResponseBodyAsString().contains("Erro não existe request ou header"));
		} catch (Exception ex) {
			assertEquals(500, ex.getMessage());
		}
	}
	
	@Test
	public void testAlteracao() {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
				
		Pessoa pessoa = buscaPessoa("2");
		pessoa.setCpf("96353112734");
		pessoa.setNome("Pessoa 1-2");
		
		pessoa.setDataNascimento("1967-12-16");
		
		pessoa.setEmail("avmartins@gmail.com");

		pessoa.setNacionalidade("Brasileira");
		pessoa.setNaturalidade("Rio de Janeiro");
		pessoa.setSexo("M");
		
		HttpEntity<Pessoa> request = new HttpEntity<>(pessoa, headers);

		final String baseUrl = "http://localhost:8090/CadastroPessoas/Alteracao/2";

		try {
			URI uri = new URI(baseUrl);
			restTemplate.exchange(uri, HttpMethod.PUT, request,Pessoa.class);
			assertNotNull(request.getBody());
			System.out.println("####################### testAlteracao #######################");
			System.out.println("Atualização Pessoa com sucesso!");
		} catch (HttpClientErrorException ex) {
			assertEquals(400, ex.getRawStatusCode());
			assertEquals(true, ex.getResponseBodyAsString().contains("Erro não existe request ou header"));
		} catch (Exception ex) {
			assertEquals(500, ex.getMessage());
		}
	}
	
	@Test
	public void testRemocao() {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
				
		Pessoa pessoa = buscaPessoa("2");
				
		HttpEntity<Pessoa> request = new HttpEntity<>(pessoa, headers);

		final String baseUrl = "http://localhost:8090/CadastroPessoas/Remocao/2";

		try {
			URI uri = new URI(baseUrl);
			restTemplate.exchange(uri, HttpMethod.DELETE, request,Pessoa.class);
			assertNotNull(request.getBody());
			System.out.println("####################### testRemocao #######################");
			System.out.println("Pessoa deletado com sucesso!\n");
		} catch (HttpClientErrorException ex) {
			assertEquals(400, ex.getRawStatusCode());
			assertEquals(true, ex.getResponseBodyAsString().contains("Erro não existe request ou header"));
		} catch (Exception ex) {
			assertEquals(500, ex.getMessage());
		}
	}

	@Test
	public void testConsultaTodos() {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<Iterable<Pessoa>> response = restTemplate.exchange("http://localhost:8090/CadastroPessoas/ConsultaTodos",
				HttpMethod.GET, null, new ParameterizedTypeReference<Iterable<Pessoa>>() {
				});

		Iterable<Pessoa> listaPessoaes = response.getBody();
		
		System.out.println("####################### testConsultaTodos #######################");

		for (Pessoa pessoa : listaPessoaes) {
			System.out.println("Nome : "+pessoa.getNome());
		}

		assertNotNull(response.getBody());
	}
	
	@Test
	public void testConsultaId() {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<Pessoa> response = restTemplate.exchange("http://localhost:8090/CadastroPessoas/ConsultaId/1",
				HttpMethod.GET, null, new ParameterizedTypeReference<Pessoa>() {
				});

		Pessoa pessoa = response.getBody();
		
		System.out.println("####################### testConsultaId #######################");

		System.out.println("Nome : "+pessoa.getNome());

		assertNotNull(response.getBody());
	}
	
	@Test
	public void testConsulta() {

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<Iterable<Pessoa>> response = restTemplate.exchange("http://localhost:8090/CadastroPessoas/Consulta/1/ID",
				HttpMethod.GET, null, new ParameterizedTypeReference<Iterable<Pessoa>>() {
				});

		Iterable<Pessoa> listaPessoaes = response.getBody();
		
		System.out.println("####################### testConsulta #######################");

		for (Pessoa pessoa : listaPessoaes) {
			System.out.println("Consulta por ID   : "+pessoa.getNome());
		}
		
		ResponseEntity<Iterable<Pessoa>> response2 = restTemplate.exchange("http://localhost:8090/CadastroPessoas/Consulta/Pessoa/NOME",
				HttpMethod.GET, null, new ParameterizedTypeReference<Iterable<Pessoa>>() {
				});

		listaPessoaes = response2.getBody();

		for (Pessoa pessoa : listaPessoaes) {
			System.out.println("Consulta por Nome : "+pessoa.getNome());
		}
		
		ResponseEntity<Iterable<Pessoa>> response3 = restTemplate.exchange("http://localhost:8090/CadastroPessoas/Consulta/77777777777/CPF",
				HttpMethod.GET, null, new ParameterizedTypeReference<Iterable<Pessoa>>() {
				});

		listaPessoaes = response3.getBody();

		for (Pessoa pessoa : listaPessoaes) {
			System.out.println("Consulta por CPF  : "+pessoa.getNome());
		}

		assertNotNull(response.getBody());
	}
	
	private Pessoa buscaPessoa(String id) {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<Pessoa> response = restTemplate.exchange("http://localhost:8090/CadastroPessoas/ConsultaId/"+id,
				HttpMethod.GET, null, new ParameterizedTypeReference<Pessoa>() {
				});

		Pessoa pessoa = response.getBody();

		return pessoa;
	}

}
