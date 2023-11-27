# Documentação de Projeto Maven 

O código fornecido é um arquivo POM (Project Object Model) do Maven. Maven é uma ferramenta de automação de compilação usada principalmente para projetos Java.

## Estrutura do Projeto

1. **ModelVersion**: Este tag define a versão do POM que está sendo usada. No nosso caso, a versão é 4.0.0.
2. **Parent**: Este tag é usado para herdar configurações de outro POM. Neste caso, o projeto está herdando do spring-boot-starter-parent.
3. **GroupId**: Identifica a organização ou grupo que está mantendo o projeto. Aqui, o groupId é com.scalesec.
4. **ArtifactId**: Identifica o nome do projeto. Neste caso, o artifactId é demo-gcu.
5. **Version**: Identifica a versão do projeto. Aqui, a versão é 0.0.1-SNAPSHOT.
6. **Name**: O nome do projeto, que é demo-gcu.
7. **Description**: Descrição do projeto, que é 'Projeto de demonstração para o Spring Boot'.

## Propriedades 

As propriedades definem a versão do Java e as configurações do SonarCloud, uma plataforma de inspeção de código usada para realizar análises automáticas de código para detectar bugs, odores de código e vulnerabilidades de segurança.

## Dependências

O projeto depende de várias bibliotecas, que são:

1. **spring-boot-starter**: Fornecido pelo Spring Boot, é um starter genérico para qualquer aplicação Spring Boot.
2. **spring-boot-starter-web**: Fornecido pelo Spring Boot para criar aplicações web, incluindo RESTful, usando Spring MVC. Usa Tomcat como o contêiner embutido por padrão.
3. **postgresql**: Driver JDBC do PostgreSQL.
4. **jsoup**: Biblioteca para trabalhar com HTML em Java. Permite analisar, manipular e extrair informações de HTML.
5. **spring-boot-starter-test**: Starter do Spring Boot para testes.
6. **jjwt**: Uma biblioteca Java para criação, leitura e validação de JSON Web Tokens (JWT).

## Build

O projeto usa o plugin `spring-boot-maven-plugin` que fornece a capacidade de criar executáveis JARs e WARs que são 'autocontidos' (ou seja, podem ser executados de forma independente, sem a necessidade de um servidor de aplicativos separado).