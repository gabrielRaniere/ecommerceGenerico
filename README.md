#  E-commerce API (Spring Boot)

API REST de um sistema de **e-commerce** desenvolvida com **Java e Spring Boot**, contendo funcionalidades de gerenciamento de usuários, produtos, carrinho de compras e pedidos.

O sistema simula o fluxo básico de uma loja virtual, permitindo que usuários adicionem produtos ao carrinho e finalizem pedidos com controle de estoque.

---

#  Tecnologias Utilizadas

* Java 17
* Spring Boot
* Spring Data JPA
* Hibernate
* Jakarta Validation
* Maven
* MySQL / H2 (dependendo da configuração)
* REST API
* JSON

---

#  Arquitetura do Projeto

O projeto segue uma **arquitetura em camadas (Layered Architecture)**:

```
Controller → Service → Repository → Database
```

Estrutura de pacotes:

```
src/main/java/com/ecommerce/ecommerceGenerico

controller
 ├─ CarrinhoController
 ├─ ProdutoController
 ├─ UsuarioController
 └─ PedidoController

service
 ├─ CarrinhoService
 ├─ ProdutoService
 ├─ UsuarioService
 └─ PedidoService

repository
 ├─ ProdutoRepository
 ├─ UserRepository
 ├─ CarrinhoRepository
 ├─ ItemRepository
 └─ PedidoRepository

entity
 ├─ UsuarioEntity
 ├─ ProdutoEntity
 ├─ ItemEntity
 ├─ CarrinhoEntity
 └─ PedidoEntity

exception
 ├─ ProdutoException
 ├─ UsuarioException
 └─ GlobalExceptionHandler
```

---

# 📦 Funcionalidades

###  Usuários

* Criar usuário
* Listar usuários
* Atualizar usuário
* Remover usuário

Cada usuário possui automaticamente um **carrinho de compras**.

---

###  Produtos

* Criar produto
* Listar produtos
* Atualizar produto
* Deletar produto
* Controle de estoque

---

###  Carrinho

* Listar itens do carrinho
* Adicionar produto ao carrinho
* Atualizar quantidade automaticamente se o produto já existir
* Validação de estoque

---

###  Pedidos

* Criar pedido a partir do carrinho
* Cálculo automático do valor total
* Atualização do estoque após compra
* Limpeza automática do carrinho após o pedido

---

#  Endpoints Principais

## Usuários

### Criar usuário

```
POST /usuario
```

Parâmetros:

```
nome
email
senha
```

---

### Listar usuários

```
GET /usuario
```

---

### Atualizar usuário

```
PUT /usuario/{usuarioId}
```

---

### Remover usuário

```
DELETE /usuario/{usuarioId}
```

---

#  Produtos

### Criar produto

```
POST /produto
```

Parâmetros:

```
nome
preco
estoque
```

---

### Listar produtos

```
GET /produto
```

---

### Atualizar produto

```
PUT /produto
```

---

### Deletar produto

```
DELETE /produto/{id}
```

---

#  Carrinho

### Listar itens do carrinho

```
GET /usuario/carrinho/{carrinhoId}
```

---

### Adicionar item ao carrinho

```
POST /usuario/carrinho/{carrinhoId}
```

Parâmetros:

```
produtoId
quantidade
```

---

#  Pedido

### Criar pedido

```
POST /usuario/pedido/{usuarioId}
```

O sistema:

* calcula o total do carrinho
* diminui o estoque dos produtos
* cria o pedido
* limpa o carrinho

---

#  Tratamento de Exceções

O sistema possui **tratamento global de exceções** utilizando:

```
@ControllerAdvice
```

Exceções implementadas:

* ProdutoException
* UsuarioException
* NoSuchElementException

Resposta padrão da API:

```json
{
  "erro": "mensagem de erro",
  "codigo": "404"
}
```

---

#  Modelo de Dados

Relacionamentos principais:

```
Usuário 1 ─── 1 Carrinho
Usuário 1 ─── N Pedidos
Carrinho 1 ─── N Itens
Produto 1 ─── N Itens
```

---

# ▶️ Como Executar o Projeto

### 1️⃣ Clonar repositório

```
git clone https://github.com/seu-usuario/ecommerce-api.git
```

---

### 2️⃣ Entrar na pasta

```
cd ecommerce-api
```

---

### 3️⃣ Rodar aplicação

```
./mvnw spring-boot:run
```

ou

```
mvn spring-boot:run
```

---

### 4 DOCUMENTAÇÃO COM SWAGGER
```
http://localhost:8080/swagger-ui/index.html
```

# 📌 Melhorias Futuras

* Implementar **Spring Security + JWT**
* Criar **DTOs para resposta da API**
* Implementar **testes unitários (JUnit / Mockito)**
* Implementar **paginação de produtos**
* Criar **sistema de autenticação de usuários**

---

# 👨‍💻 Autor

Desenvolvido por **Gabriel Raniere**

Projeto criado para fins de estudo e prática com **Spring Boot, APIs REST e JPA**.
