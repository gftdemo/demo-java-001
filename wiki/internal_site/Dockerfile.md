# Documentação do Código

Aqui está a documentação detalhada para o código fornecido:

```Dockerfile
FROM nginx:alpine
COPY . /usr/share/nginx/html
```

## Explicação

Este é um arquivo Dockerfile que é usado para criar uma imagem Docker. Docker é uma plataforma de software que permite que você crie, teste e implemente aplicações rapidamente usando recipientes.

Aqui está o que cada linha do código faz:

### `FROM nginx:alpine`

Esta linha indica que a imagem Docker base será `nginx:alpine`. Aqui, `nginx` é a imagem base e `alpine` é a tag da imagem. A imagem `nginx:alpine` é uma versão minimalista do nginx, que é um servidor web popular. A versão `alpine` é baseada na distribuição Alpine Linux, que é conhecida por ser leve e segura.

### `COPY . /usr/share/nginx/html`

Esta linha copia os arquivos do diretório atual (no sistema de arquivos do host) para o caminho `/usr/share/nginx/html` dentro do contêiner. No caso do nginx, este é o diretório padrão onde ele espera encontrar arquivos para servir na web.

Assim, em resumo, este Dockerfile cria um contêiner Docker que pode servir os arquivos do diretório atual através de um servidor web nginx.