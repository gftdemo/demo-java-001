# Documentação do código Javascript de Login

Este script Javascript é usado para autenticar um usuário. Ele usa jQuery para manipular o DOM e realizar a requisição AJAX.

```javascript
$(document).ready(function(){
  $("#btn-login").click(function(){
    var username = $('#inputUsername').val();
    var password = $('#inputPassword').val();
    var payload = {username: username, password: password};

    $.ajax({
      type: 'POST',
      url: "http://localhost:8080/login",
      data: JSON.stringify(payload),
      dataType: "json",
      contentType: "application/json"
    })
    .fail(function(data){
      alert("Whoops, try again");
    })
    .done(function(data){
      localStorage.jwt = data.token;
      var username = JSON.parse(atob(data.token.split('.')[1]))['sub'];
      localStorage.username = username;
      window.location.replace("index.html");
    })
  })
});
```

## Explicação detalhada do código

- `$(document).ready(function(){...});` - Este código garante que o script só será executado depois que o documento HTML estiver completamente carregado.
- `"#btn-login".click(function(){...});` - Este código diz que quando o botão de login for clicado, a função anônima será executada.
- `var username = $('#inputUsername').val();` - Este código busca o valor inserido no campo de nome de usuário.
- `var password = $('#inputPassword').val();` - Este código busca o valor inserido no campo de senha.
- `var payload = {username: username, password: password};` - Este código cria um objeto com o nome de usuário e senha.
- `$.ajax({...})` - Este código realiza uma requisição AJAX para o servidor.
- `type: 'POST',` - Este código define o tipo da requisição como POST.
- `url: "http://localhost:8080/login",` - Este código define a URL para a qual a requisição será enviada.
- `data: JSON.stringify(payload),` - Este código converte o objeto payload em uma string JSON e a envia como parte da requisição.
- `dataType: "json",` - Este código define que o tipo de dados esperado em resposta é JSON.
- `contentType: "application/json"` - Este código define o tipo de conteúdo enviado para o servidor como JSON.
- `.fail(function(data){...});` - Esta função será chamada se a requisição falhar. Ela exibe um alerta com a mensagem "Whoops, try again".
- `.done(function(data){...});` - Esta função será chamada se a requisição for bem-sucedida. Ela armazena o token JWT no armazenamento local, extrai o nome de usuário do token e o armazena no armazenamento local e, finalmente, redireciona o usuário para a página "index.html".