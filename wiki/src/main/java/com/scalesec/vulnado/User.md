# Documentação do Código

O código abaixo pertence à classe `User` que faz parte do pacote `com.scalesec.vulnado`. Esta classe é responsável por manipular informações de usuário, incluindo autenticação e recuperação de informações de usuário do banco de dados.

## Importações

O código importa várias bibliotecas para conexão com banco de dados, manipulação de JWT (JSON Web Tokens) e manipulação de chaves criptográficas.

## Classe User

A classe User possui três atributos: `id`, `username` e `hashedPassword`. Cada instância da classe User representa um usuário único.

### Construtor

O construtor da classe User recebe três parâmetros: `id`, `username` e `hashedPassword`. Esses valores são então atribuídos aos atributos correspondentes do objeto User.

### Método token()

Este método recebe uma string `secret` como parâmetro e retorna um token JWT. Este token é construído utilizando a biblioteca `Jwts`, que codifica o nome de usuário (`username`) no token e assina o token utilizando a chave secreta fornecida.

### Método assertAuth()

Este método estático recebe uma string `secret` e um `token` como parâmetros. Ele tenta decodificar o token usando a chave secreta fornecida. Se o token não puder ser decodificado corretamente (indicando que o token é inválido ou não foi assinado com a chave correta), o método lança uma exceção `Unauthorized`.

### Método fetch()

Este método estático recebe uma string `un` (presumivelmente um nome de usuário) como parâmetro. Ele então tenta buscar um usuário com esse nome de usuário no banco de dados. Se o usuário for encontrado, um novo objeto User é criado com as informações recuperadas do banco de dados e retornado. Se o usuário não for encontrado, o método retorna `null`. Se ocorrer algum erro durante a execução do método, a exceção é impressa na saída de erro padrão e o método ainda retorna `null`.