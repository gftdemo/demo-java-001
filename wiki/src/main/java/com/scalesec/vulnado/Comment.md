# Documentação do código

## Pacote
```java
package com.scalesec.vulnado;
```
Este é o pacote onde a classe `Comment` está localizada.

## Importações
```java
import org.apache.catalina.Server;
import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;
```
Essas são as importações necessárias para a classe `Comment`. `java.sql.*` é usado para operações relacionadas ao banco de dados. `java.util.*` é usado para operações relacionadas à data e manipulação de listas.

## Classe Comment
```java
public class Comment {
```
Esta é a classe principal chamada `Comment`. Ela representa um comentário em algum tipo de aplicativo ou site.

### Atributos da classe
```java
public String id, username, body;
public Timestamp created_on;
```
A classe `Comment` tem quatro atributos:
- `id`: identificador único do comentário.
- `username`: nome do usuário que fez o comentário.
- `body`: o conteúdo do comentário.
- `created_on`: a data e hora em que o comentário foi criado.

### Construtor da classe
```java
public Comment(String id, String username, String body, Timestamp created_on) {
  this.id = id;
  this.username = username;
  this.body = body;
  this.created_on = created_on;
}
```
Este é o construtor da classe `Comment`. Ele é usado para criar uma nova instância de um comentário.

### Método create
```java
public static Comment create(String username, String body){
```
Este método estático `create` é usado para criar e salvar um novo comentário no banco de dados.

### Método fetch_all
```java
public static List<Comment> fetch_all() {
```
Este método estático `fetch_all` é usado para buscar todos os comentários do banco de dados.

### Método delete
```java
public static Boolean delete(String id) {
```
Este método estático `delete` é usado para deletar um comentário específico do banco de dados.

### Método commit
```java
private Boolean commit() throws SQLException {
```
Este é um método privado `commit` que é usado para inserir um novo comentário no banco de dados. Ele é chamado dentro do método `create`.