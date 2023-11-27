# Documentação do Código HTML

O código HTML apresentado é usado para criar uma página web simples que serve como um diretório interno para uma empresa. 

```html
<html>
 <body>
   <h1>Internal Directory</h1>

   <ul>
     <li>CEO - <a href="mailto:alice@example.com?subject=feedback">email me</a>
     <li>COO - <a href="mailto:bob@example.com?subject=feedback">email me</a>
     <li>CTO - <a href="mailto:tom@example.com?subject=feedback">email me</a>
     <li>CISO - <a href="mailto:eve@example.com?subject=feedback">email me</a>
   </ul>
 </body>
</html>
```

## Estrutura do Código

- `<html>`: Este é o elemento raiz de uma página HTML.

- `<body>`: Este elemento contém o conteúdo principal que é mostrado aos usuários quando eles visitam a página.

- `<h1>`: Este é um elemento de cabeçalho que geralmente contém o título principal ou a principal manchete da página. Neste caso, ele contém o texto "Internal Directory".

- `<ul>`: Este elemento define uma lista não ordenada (ou seja, uma lista cujos itens não estão em uma ordem particular). 

- `<li>`: Este elemento define um item de lista. Neste caso, cada item da lista contém o título de um executivo da empresa (por exemplo, "CEO") e um link para enviar um e-mail a esse executivo.

- `<a href="mailto:alice@example.com?subject=feedback">email me</a>`: Este é um link que, quando clicado, abre o cliente de e-mail padrão do usuário com um novo e-mail em branco endereçado ao endereço de e-mail especificado (neste caso, alice@example.com). O assunto do e-mail é preenchido automaticamente com a palavra "feedback". O texto visível para o link é "email me".

## Observações

- O código HTML acima não é completo sem um fechamento adequado de cada tag `<li>`. Portanto, cada linha contendo a tag `<li>` deve terminar com `</li>` para evitar possíveis problemas de renderização no navegador.
