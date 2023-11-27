# Documentação do Código

```javascript
$(document).ready(function(){
  var source = $("#comment-template").html();
  var template = Handlebars.compile(source);
```
Neste trecho, estamos preparando o documento para ser manipulado e compilando o template de comentário usando a biblioteca Handlebars.js.

```javascript
  // Add JWT to every request
  $.ajaxSetup({ beforeSend: function(xhr) {
    xhr.setRequestHeader('x-auth-token', localStorage.jwt);
  }});
```
Aqui, estamos configurando todas as solicitações AJAX para incluir o token JWT no cabeçalho. Isso é útil para autenticação em APIs.

```javascript
  // Helper Functions
  function setupDeleteCommentHandler() {
    // NOTE: This needs to come first since comments aren't loaded yet.
    $('.delete-comment').click(function(){
      var parent = this.closest(".row");
      var id = $(parent).data("comment_id");
```
A função `setupDeleteCommentHandler` é uma função auxiliar que define um manipulador de eventos para o clique do botão de exclusão de comentário. Obtém o ID do comentário a ser excluído.

```javascript
      $.ajax({
        type: "DELETE",
        url: "http://localhost:8080/comments/" + id
      }).done(function(){
        $(parent).remove();
      });
    });
  }
```
Em seguida, fazemos uma solicitação AJAX para excluir o comentário na API. Se a solicitação for bem-sucedida, o comentário é removido da interface do usuário.

```javascript
  function fetchComments() {
    $.get("http://localhost:8080/comments", function(data){
      $('#comments-container').html('')
      data.forEach(function(comment){
        if (comment.body.indexOf("<script>") < 0) {
          $("#comments-container").append(template(comment));
        }
      });
      setupDeleteCommentHandler();
    });
  }
```
A função `fetchComments` é usada para buscar os comentários da API. Para cada comentário, verificamos se ele não contém uma tag de script (para evitar ataques de script cross-site) antes de adicioná-lo ao contêiner de comentários.

```javascript
  //Event Handlers
  $('#submit-comment').click(function(){
    var comment = $('#new-comment').val();
    var username = localStorage.username;
    $.ajax({
      type: "POST",
      url: "http://localhost:8080/comments",
      data: JSON.stringify({username: username, body: comment}),
      dataType: "json",
      contentType: "application/json",
    }).done(function(){
        $('#new-comment').val('');
        fetchComments();
    });
  });
```
Aqui, temos um manipulador de eventos que é acionado quando o botão de envio de comentário é clicado. Ele faz uma solicitação AJAX para criar um novo comentário na API.

```javascript
  $('#signout').click(function(){
    alert("Goodbye!");
    localStorage.jwt = '';
    localStorage.username = '';
    window.location.replace("login.html")
  });
```
Este é o manipulador de eventos para o botão de logout. Ele limpa o token JWT e o nome de usuário do armazenamento local e redireciona o usuário para a página de login.

```javascript
  // Initialize
  if (localStorage.getItem("jwt")){
    fetchComments();
  } else{
    window.location.replace("login.html");
  }
});
```
Finalmente, quando o documento está pronto, verificamos se o usuário está logado (verificando se o token JWT existe no armazenamento local). Se estiverem logados, buscamos os comentários. Se não, redirecionamos o usuário para a página de login.
