# Projeto Back-End

## Tecnologias utilizadas

- Java  17
- Spring boot 3.4
- Spring Security + JWT
- Spring Data JPA
- Flyway
- SGBD PostgreSQL
- OpenAPI (Swagger)
- Git/GitHub
- JavaMail (Notificação via e-mail)

## Visão Geral - Aplicação Web para o Processo de Recrutamento Interno de Colaboradores

- CRUD de Usuários
- CRUD de Vagas
- Dashboard de Candidaturas

A aplicação permite o gerenciamento dos itens acima com base no papel do usuário logado, sendo dois: ADMIN e COLABORADOR, com suas devidas permissões.

## Fluxo desejado para o (ADMIN):

- Acesso ao sistema via autenticação.
- Criação/Manipulação das informações dos usuários (colaborador ou admin). **Observação:** ao acessar os dados dos usuários cadastrados, a informação da senha só fica visível para o próprio usuário.
- Criação/Manipulação das informações das vagas.
- Gerenciamento das candidaturas cadastradas (aprovação, reprovação e feedbacks).
  
## Fluxo desejado para o (COLABORADOR):

- Acesso ao sistema via autenticação.
- Gerenciamento das informações do seu próprio perfil de usuário.
- Visualização e opção de se candidatar a uma vaga. **Observação:** a candidatura só pode ser realizada se a vaga estiver aberta.
- Visualização das suas próprias candidaturas cadastradas (incluindo o feedback).


## Dados Técnicos: 
As principais configurações da aplicação estão localizadas no arquivo application.properties, dentro de src/main/resources. Exemplo: configuração do banco de dados, tempo de expiração do JWT e informações do SMTP. Para rodar a aplicação em seu ambiente - local, basta configurar um banco de dados PostgreSQL com base nos dados contidos nesse arquivo.

Para acessar a documentação da API, você pode utilizar o Swagger/OpenAPI. Com a aplicação iniciada, acesse http://localhost:8080/swagger-ui/index.html.

 Para facilitar os testes, foi utilizada a ferramenta Flyway. Dentro de src/main/resources/db/migration, há um arquivo chamado V202412121302__insert_registros_tabela_usuario.sql. Dentro desse arquivo, há dois inserts de usuários: um ADMIN e outro COLABORADOR, que servem como ponto de partida. Para utilizar o Flyway, primeiro inicialize a aplicação backend. Após a criação das tabelas do banco de dados, pare a aplicação, vá até o arquivo application.properties e altere o spring.flyway.enabled=false para spring.flyway.enabled=true, salve e inicialize novamente a aplicação.
 
 
 Caso os registros não sejam gerados automaticamente, ou você não deseje utilizar Flyway, basta copiar e executar as duas linhas abaixo:
```sql
INSERT INTO usuario(email, nome, senha, tipo_usuario) 
VALUES ('colaborador@colaborador.com', 'Colaborador',
                    '$2a$10$FXyca6aPuXkcvKLydm6zeO7/hilkTmIlQMbnhy8wYEKBQhg6bqUSu', 'COLABORADOR');

INSERT INTO usuario(email, nome, senha, tipo_usuario) 
VALUES ('admin@admin.com', 'Administrador',
                           '$2a$10$FXyca6aPuXkcvKLydm6zeO7/hilkTmIlQMbnhy8wYEKBQhg6bqUSu', 'ADMIN');
```
## Para Login:

- Email: admin@admin.com
- Senha: 123
  
ou

- Email: colaborador@colaborador.com
- Senha: 123



