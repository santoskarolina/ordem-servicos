# :computer: :pushpin:Banckend Sistema Ordem de Serviços. 

#### Um usuário pode adiconar seus serviços com seus respectivos clientes.

#### :small_blue_diamond: Tecnologias usadas no desenvolvimento:
- Java no SpringBoot
- Maven como gerenciador de dependências
- Postman para teste de requisições
- banco de h2 para testes
- banco postgresql para desenvolvimento e produção
- deploy da api no Heroku

##### :small_blue_diamond: Métodos para entidade Clientes:
- inserir
- deletar (se não houver serviços associados)
- encontrar todos
- encontrar pelo ID
- atualizar

##### :small_blue_diamond: Métodos para entidade Serviços:
- inserir
- deletar (se não houver cliente associado)
- encontrar todos
- encontrar pelo ID
- atualizar

##### Endpionts

#### retornar todos os clientes

```http
  GET /clientes
```

#### retornar um cliente por id
```http
  GET /clientes/${id}
```

#### inserir um novo serviço
```http
  POST /servicos
```

```http
  {
    "descricao": "Formatar notebook",
    "clienteId": 1,
    "valor": 1160.0
  }
```

#### :small_blue_diamond: Modelo de camadas
![DOMAIN MODEL](https://github.com/santoskarolina/html/blob/main/uml/estrutura-de-camadas.png)
