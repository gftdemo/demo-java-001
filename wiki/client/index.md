# Documentação do Código

Este é um arquivo HTML básico, com várias partes importantes a serem observadas:

## Estrutura da Página

A estrutura básica da página consiste em uma declaração `doctype`, tags `html`, `head` e `body`.

- `<!doctype html>`: Define o documento para ser um documento HTML5.
- `<html lang="en">`: Define a linguagem do documento como inglês.

## Cabeçalho (`<head>`)

O cabeçalho contém metadados e links para CSS e JavaScript.

- `<meta charset="utf-8">`: Define a codificação de caracteres do documento.
- `<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">`: Garante que a página seja bem renderizada em dispositivos móveis.
- `<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"...>`: Importa os estilos do Bootstrap.
- `<link href="css/main.css" rel="stylesheet">`: Importa o arquivo CSS personalizado do projeto.
- `<title>Vulnado</title>`: Define o título da página que aparecerá na guia do navegador.

## Corpo (`<body>`)

O corpo da página contém o conteúdo da página e links para JavaScript.

- `<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">...</nav>`: Cria uma barra de navegação na parte superior da página.
- `<main role="main" class="container">...</main>`: Define a área principal da página. Contém o conteúdo da página, incluindo um formulário de comentário e um lugar para exibir comentários.
- `<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>`: Importa a biblioteca jQuery.
- `<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"...></script>`: Importa a biblioteca Popper.js, necessária para algumas funcionalidades do Bootstrap.
- `<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"...></script>`: Importa o JavaScript do Bootstrap.
- `<script src="http://builds.handlebarsjs.com.s3.amazonaws.com/handlebars.min-v4.1.0.js"></script>`: Importa a biblioteca Handlebars.js, que é usada para gerar o HTML dos comentários.
- `<script src="js/index.js"></script>`: Importa o arquivo JavaScript personalizado do projeto.
- `<script id="comment-template" type="text/x-handlebars-template">...</script>`: Define o template Handlebars para os comentários.

## Formulário de Comentário

O formulário de comentário permite que os usuários insiram um comentário e o enviem.

- `<input type="text" id="new-comment" class="form-control" placeholder="Comment">`: Cria um campo de entrada de texto onde os usuários podem digitar seus comentários.
- `<button id="submit-comment" class="btn btn-primary">Submit</button>`: Cria um botão que os usuários podem clicar para enviar seus comentários.

## Área de Comentário

A área de comentário exibe os comentários dos usuários.

- `<div id="comments-container"></div>`: Define uma área onde os comentários serão exibidos.
- `<div class="row" data-comment_id="{{id}}">...</div>`: Cada comentário é exibido em sua própria linha. O atributo `data-comment_id` é usado para armazenar o ID do comentário, para que ele possa ser referenciado posteriormente no JavaScript.