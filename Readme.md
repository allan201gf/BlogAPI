# BlogAPI

Projeto de uma API para gerenciamento de postagens em um blog.<br/>
O intuito desta API é utilizar-se de diversas opções do SpringBoot de modo a aprimorar minhas habilidades com a tecnologia.

A documentação dos endpoints é feita via Swagger e pode ser acessada no link abaixo após o start do servidor:

> http://localhost:80/swagger-ui.html

## Features

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





* Criação e exclusão de user sem SpringSecurity [✔] (Inativado)
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
* Login via Bearer token no Swagger [✔] (necessário obter o token via endpoint de login e digitar "Bearer " na frente)
* ...

## 🛠️ Em desenvolvimento com

* [IntelliJ](http://www.dropwizard.io/1.0.2/docs/) - A IDE do ❤️
* [Maven](https://maven.apache.org/) - Gerenciador de dependências
* [SpringBoot](https://start.spring.io/) - Framework para aplicações web
* [H2 DataBase](https://www.h2database.com/html/main.html) - Banco de dados em memória
* [Lombok](https://projectlombok.org/) - Facilitar a criação de construtores
* [Swagger](https://swagger.io/tools/open-source/open-source-integrations/) - Listagem endpoints da API

## 📄 Licença

The MIT License (MIT)

---
⌨️ com ❤️ por [Allan Garcia Ferreira](https://github.com/allan201gf) 