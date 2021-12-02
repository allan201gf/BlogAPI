# BlogAPI

Projeto de uma API para gerenciamento de postagens em um blog utilizando autenticação.<br/>
O intuito desta API é utilizar-se de diversos recursos do SpringBoot, incluindo Spring Security de modo a aprimorar minhas habilidades com a tecnologia.



A documentação dos endpoints é feita via Swagger no link abaixo e algumas informações podem ser consultadas neste mesmo arquivo:

> http://localhost:80/swagger-ui.html

## Features
Implementaçõs do sistema, para consultar os modelos de respostas da aplicação deve-se utilizar o Swagger no link acima.<br>

### Registro e Login de usuários
Para criar um post na plataforma é necessário que o usuário esteja logado.
#### Cadastro de usuário
Para cadastrar um usuário devemos bater no endpoint:

~~~
POST /api/user/v1
~~~
Dados no body:
~~~
{
    "name": "Nome do novo usuário",
    "birthDate": "02/01/1998",
    "email": "email@email.com",
    "login": "logindousuário",
    "password": "senhadousuário"
}
~~~

#### Login de usuário
~~~
POST /api/user/v1/login
~~~
Dados no body:
~~~
{
    "login": "loginteste",
    "password": "senhateste"
}
~~~
Após efetuar o login o sistema retornará o Bearer Token que deve ser enviado no header em todas as requisições que requerem o usuário logado.

### Listagem de usuários do sistema
Este endpoint retorna todos os usuários cadastrados no sistema.
> Para utilizar este endpoint o usuário deve estar logado
~~~
GET /api/user/v1
~~~

### Dados do usuário logado
Este endpoint retorna os dados do usuário que está logado.
> Para utilizar este endpoint o usuário deve estar logado
~~~
GET  /api/user/v1/getloggeduser
~~~

### Deletar usuário logado
Utilize este endpoint para deletar o usuário que está logado.
> Para utilizar este endpoint o usuário deve estar logado
~~~
DELETE  /api/user/v1/delete
~~~

### Tags
As tags são utilizadas como categoria para os posts, elas podem ser criadas a partir de endpoints especificos, ou se na criação do post ela ainda não exista, será criada automaticamente.

#### Criação de tags
> Para utilizar este endpoint o usuário deve estar logado
~~~
POST  /api/tag/v1
~~~
Dados no body:
~~~
{
  "nameTag": "nomeDaTag"
}
~~~
#### Listagem das tags
> Para utilizar este endpoint o usuário deve estar logado
~~~
GET  /api/tag/v1
~~~
#### Deletar tag
> Para utilizar este endpoint o usuário deve estar logado
~~~
DELETE  /api/tag/v1/delete/{tagId}
~~~
#### Listagem de posts a partir de uma tag
> Para utilizar este endpoint o usuário deve estar logado
~~~
GET  /api/tag/v1/searchPostByTag/{nameTag}
~~~

### Posts
O gerenciamento dos posts ficam por conta os usuários logados, a exibição e pesquisa podem ser acessados sem a autenticação.

#### Criação de posts
> Para utilizar este endpoint o usuário deve estar logado
~~~
POST  /api/post/v1
~~~
Dados no body:
~~~
{
    "title": "primeiro post",
    "postBody": "Lorem Ipsum is simply dummy text of the printing and typesetting industry.
    Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley
    of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap
    into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of
    Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMake
    including versions of Lorem Ipsum.",
    "tag": [
        "Tecnologia",
        "Iphone"
        ]
}
~~~
#### Exclusão de posts
> Para utilizar este endpoint o usuário deve estar logado
~~~
DELETE  /api/post/v1/{id}
~~~

#### Listagem de posts
Este endpoint retorna todos os posts de forma completa.<br>
Os posts de retorno não contaram como uma visualização.
~~~
GET  /api/post/v1
~~~
#### Listagem resumida de posts
Este endpoint retorna todos os posts de forma resumida, são exibidos apenas os primeiros 120 caracteres.<br>
Os posts de retorno não contaram como uma visualização.
~~~
GET  /api/post/v1/allPostAbstract
~~~
#### Listagem resumida dos posts mais acessados
Este endpoint retorna os posts mais acessados de forma resumida.<br>
Os posts de retorno não contaram como uma visualização.
~~~
GET  /api/post/v1/postMostAccessed
~~~
#### Acesso de post por id
O post de retorno contará uma visualização.
~~~
GET  /api/post/v1/searchPostById/{id}
~~~
#### Pesquisa de posts por data de publicação
Este endpoint retorna os posts que foram publicados dentro do intervalo de tempo informado.<br>
Os posts de retorno não contaram como uma visualização.
~~~
GET  /api/post/v1/searchPostByTimeInterval?dateStart=01/01/2010&dateEnd=01/01/2030
Alterar dateStart e dateEnd
~~~
#### Pesquisa de posts pelo título
Este endpoint retorna os posts que possuem em seu título o termo de pesquisa.<br>
Os posts de retorno não contaram como uma visualização.
~~~
GET  http://localhost/api/post/v1/searchPostByTitle?title=TextoDaPesquisa
~~~
#### Edição de posts
Utilize este endpoint para editar o conteúdo de um post especifico informando seu Id.<br>
Informe no body da requisição apenas os itens que deseja alterar.<br>
É possível editar apenas os posts que foram cadastrados pelo usuário logado.
> Para utilizar este endpoint o usuário deve estar logado
~~~
PATCH  /api/post/v1/updatePostById/{IdPost}
~~~
Dados no body:
~~~
{
    "title": "primeiro post editado",
    "postBody": "Editado Lorem Ipsum is simply dummy text of the printing and typesetting industry.
    Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley
    of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap
    into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of
    Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMake
    including versions of Lorem Ipsum.",
    "tag": [
        "Tecnologia",
        "Carro"
        ]
}
~~~

### Futuras implementações

* Criação do usuário administrador que possa manipular qualquer post publicado por qualquer usuário do sistema.
* ...

## 🛠️ Em desenvolvimento com

* [IntelliJ](http://www.dropwizard.io/1.0.2/docs/) - A IDE do ❤️
* [Maven](https://maven.apache.org/) - Gerenciador de dependências
* [SpringBoot](https://start.spring.io/) - Framework para aplicações web
* [H2 DataBase](https://www.h2database.com/html/main.html) - Banco de dados em memória
* [Lombok](https://projectlombok.org/) - Facilitar a criação de construtores
* [Swagger](https://swagger.io/tools/open-source/open-source-integrations/) - Listagem endpoints da API
* [Spring Security](https://spring.io/projects/spring-security) - Autenticação de usuários
* [Token JWT](https://github.com/jwtk/jjwt) - Criação do token de autenticação de usuários

## 📄 Licença

The MIT License (MIT)

---
⌨️ com ❤️ por [Allan Garcia Ferreira](https://github.com/allan201gf) 