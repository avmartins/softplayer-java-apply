# Cadastro de Pessoas

Foi crido uma API REST que será usada para o controle do registro da Pessoa, bem como os exames recebidos.

Foi Criado um sistema web que comporta toda a funcionalidade pedida para o controle do registro da Pessoa, bem como os exames recebidos.

## REST API

 * CadastroPessoasRestController
 
  * Cadastro de Pessoa    - url : http://localhost:8090/CadastroPessoas/Cadastro
  * Alteração de Pessoa   - url : http://localhost:8090/CadastroPessoas/Alteracao/{id}
  * Remoção de Pessoa     - url : http://localhost:8090/CadastroPessoas/Remocao/{id}
  * Consulta de Pessoas   - url : http://localhost:8090/CadastroPessoas/Consulta/{valor}/{campo} - Ex : ID/1 | NOME/Teste | CPF/99999999999
  
  cadastro, alteração, remoção e consulta de pessoas 

## Arquitetura do Sistema
 
 *** Para a configuração da aplicação foi utilizado o Apache Maven, para isso foram adicionadas dependências do mesmo:

  * Spring Boot 2.1.5.RELEASE;
  * Spring MVC - que adiciona as dependências do Spring MVC;
  * Spring Security - para o desenvolvimento da segurança e login;
  * thymeleaf - para o desenvolvimento da visão;
  * data-jpa - que adiciona as dependências do Spring Data;
  * log4j - que adiciona as dependências do log;
  * test - que adiciona as dependências do test;
  * com.h2database, que é o banco de dados que será utilizado nessa aplicação.
  
## Executar o Sistema Linha de comando

  ### Passo 1

  #### Abra CMD ou Power Shell
  
  #### Ir para o diretorio do projeto
  
  #### Execute : mvn spring-boot:run ( Sobe a aplicação na porta 8090 : http://localhost:8090/  )
 
  #### Abrir url http://localhost:8090/ e navegar e testar usando o arquivo de Evidencias.doc
  
  #### Abrir url http://localhost:8090/h2 se quiser ver o banco ( jdbc:h2:mem:testedb )
  
  ### Passo 2

  #### Abra um segundo CMD ou Power Shell
	
  #### Ir para o diretorio do projeto
  
  #### Execute : mvn test 
  
  ##### Vai executar a Classe de Test CadastroPessoasRestControllerTest que testa a API CadastroPessoasRestController
  
## Executar o Sistema IDE

  ### Abra idea da sua escolha - Foi utilizada a IDE Spring Tools 4
  
  ### Importa o projeto
  
  ### Executa Spring boot App
  
  #### Abrir url http://localhost:8090/ e navegar e testar usando o arquivo de Evidencias.doc
  
  #### Abrir url http://localhost:8090/h2 se quiser ver o banco ( jdbc:h2:mem:testedb )
  
  ### Executa a Classe de Test CadastroPessoasRestControllerTest que testa a API CadastroPessoasRestController

