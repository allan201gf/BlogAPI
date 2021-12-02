# BlogAPI

Projeto de uma API para gerenciamento de postagens em um blog.<br/>
O intuito desta API é utilizar-se de diversas opções do SpringBoot de modo a aprimorar minhas habilidades com a tecnologia.

A documentação dos endpoints é feita via Swagger e pode ser acessada no link abaixo após o start do servidor:

> http://localhost:80/swagger-ui.html

## Features
Implementaçõs do sistema, para consultar os modelos de respostas da aplicação deve-se utilizar o Swagger no link acima.

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
Após efetuar o login o sistema retornará o Bearer Token que deve ser enviado no header em todas as requisições que requerem o usuário logado

### Listagem de usuários do sistema
Este endpoint retorna todos os usuários cadastrados no sistema
> Para utilizar este endpoint o usuário deve estar logado
~~~
GET /api/user/v1
~~~

### Dados do usuário logado
Este endpoint retorna os dados do usuário que está logado
> Para utilizar este endpoint o usuário deve estar logado
~~~
GET  /api/user/v1/getloggeduser
~~~

### Deletar usuário logado
Utilize este endpoint para deletar o usuário que está logado
> Para utilizar este endpoint o usuário deve estar logado
~~~
DELETE  /api/user/v1/delete
~~~





### Itens a serem incluidos na documentação

* Criação e exclusão de tags de forma manual [✔]
* Exibição de todas as tags [✔]
* Caso a tag escolhida no post não exista, cria-la automaticamente [✔]
* Criação e exclusão de posts [✔]
* Exibição de todos os posts [✔]
* Pesquisa por posts a partir de uma determinada tag [✔]
* Pesquisa por post pelo título [✔]
* Visualização resumida dos posts [✔]
* Filtragem de posts por data [✔]
* Contagem de acessos de cada post [✔] (A contagem só é incrementada quando um post é acessado de forma completa)
* Exibir posts mais acessados [✔] (retorna os 5 posts mais acessados)
* Edição de posts [✔]
* Travar a exclusão de tags que possuem posts associados [✔]
* Métodos para registrar e logar no sistema [✔]
* Implementação para apenas o usuário que criou o post poder edita-lo [✔]
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