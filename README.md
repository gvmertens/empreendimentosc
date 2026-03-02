# Emprendimentos SC

Este projeto é uma aplicação Spring Boot projetada como modelo de inscrição do candidato Guilherme Mertens, para o programa IA PARA DEVS do SCTEC. É um projeto de controle de empreendimentos fornecendo APIs REST para operações básicas de CRUD em entidades como Pessoa, Empreendimento e Segmento. A solução oferece uma estrutura modular e escalável, com foco em boas práticas de desenvolvimento e separação de responsabilidades.

São empregados a titulo de demonstração, os principais verbos usados em API's REST, como: GET, POST, PUT, DELETE e PATCH, bem como foram respeitados os response codes padrão do mercado, como 200, 400, 204 e etc.

## Descrição da Solução

A aplicação foi desenvolvida para permitir o cadastro, consulta, atualização e exclusão de empreendimentos, pessoas e segmentos. Cada entidade possui endpoints específicos controlados por classes `@RestController` e o acesso aos dados é feito via `JpaRepository`. Exceções personalizadas garantem tratamento de erros adequado (por exemplo, `NotFoundException`).

O projeto foi concebido de forma simples a mostrar os conhecimentos do inscrito, sem se preocupar em cobrir todos os possíveis cenários, somente entregando exemplos de operações, filtros, JPA e demais pré-requisitos. Foi configurado também um swagger para a aplicação (Acessível em http://localhost:8080/swagger-ui/index.html) e o API DOCS (Acessível em http://localhost:8080/v3/api-docs).

Para fins de testes, foi disponibilizado dentro de resources, a collections do Postman que pode ser usada para testar as principais funcionalidades.

## Tecnologias Utilizadas

- Java 21
- Spring Boot
- Spring Data JPA (Hibernate)
- Maven
- H2 (in-memory) ou outro banco configurável via `application.yaml`
- Lombok (para reduzir boilerplate) - ver classe `BaseEntity`.

## Estrutura Geral do Projeto

O código fonte está organizado conforme padrão Maven:

```
src/
  main/
    java/br/com/mertens/emprendimentosc/
      controller/           # APIs REST
      dto/                  # Objetos de transferência (Request/Response)
      entity/               # Modelos JPA
      enums/                # Enums de domínio (ex: DominioAtivoInativo)
      exception/            # Exceções customizadas
      repository/           # Repositórios JPA
      service/              # Lógica de negócio
  resources/
    application.yaml      # Configurações da aplicação
    static/               # Recursos estáticos
    templates/            # Templates (se aplicável)

```

O pacote principal contém a classe `EmprendimentoscApplication` anotada com `@SpringBootApplication`.

## Instruções de Execução

1. **Pré-requisitos**: Java 21 e Maven instalados.
2. **Compilar e executar**: abra um terminal na raiz do projeto e execute:
   ```sh
   mvn clean spring-boot:run
   ```
   ou:
   ```sh
   mvn clean package
   java -jar target/emprendimentosc-0.0.1-SNAPSHOT.jar
   ```
3. **Configuração**: edite `src/main/resources/application.yaml` para ajustar parâmetros de banco ou porta.
4. **Testes**: para rodar os testes automatizados:
   ```sh
   mvn test
   ```

Após iniciado, a API estará disponível em `http://localhost:8080` por padrão. Explore os endpoints via Postman, curl ou interface Swagger (se configurada).

## Vídeo Pitch

Confira o vídeo pitch desta solução:

[Assista ao vídeo pitch](https://youtu.be/2SxBN_FxE_M)

---

Este README contém detalhes essenciais para compreender, configurar e executar a aplicação.
