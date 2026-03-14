# 🛡️ Guilda de Aventureiros — Registro Oficial (TP1)

API REST construída com **Spring Boot (Java 25)** para gerenciar o cadastro oficial de Aventureiros e Companheiros da Guilda. Trabalho Prático 1 da disciplina de Sistemas Escaláveis.

---

## 📋 Requisitos do Trabalho e Como Foram Cumpridos

### Domínio

| Requisito | Implementação |
|---|---|
| Aventureiro com `id`, `nome`, `classe`, `nivel`, `ativo`, `companheiro` | Classe `Aventureiro` em `domain/Aventureiro.java` |
| Companheiro com `nome`, `especie`, `lealdade` | Classe `Companheiro` em `domain/Companheiro.java` — existe apenas como composição do Aventureiro |
| Enum `Classe`: `GUERREIRO, MAGO, ARQUEIRO, CLERIGO, LADINO` | `domain/Classe.java` |
| Enum `Especie`: `LOBO, CORUJA, GOLEM, DRAGAO_MINIATURA` | `domain/Especie.java` |

### Regras de Negócio

| Requisito | Implementação |
|---|---|
| `id` gerado pelo sistema | `AventureiroRepository` usa `AtomicLong` como gerador de ID sequencial |
| `nome` obrigatório e não vazio | `@NotBlank` em `AventureiroRequest` |
| `classe` deve pertencer ao enum | Desserialização do Jackson rejeita valores inválidos; `GlobalExceptionHandler` captura o erro |
| `nivel` >= 1 | `@Min(1)` em `AventureiroRequest` |
| Aventureiro criado como **ativo** | Construtor `Aventureiro(nome, classe, nivel)` define `ativo = true` automaticamente |
| Aventureiro inativo permanece no sistema | `DELETE /aventureiros/{id}` usa `setAtivo(false)`, nunca remove da lista |
| Validações do companheiro (`nome`, `especie`, `lealdade` entre 0–100) | `@NotBlank`, `@NotNull`, `@Min(0)`, `@Max(100)` em `CompanheiroRequest` |
| Recurso não encontrado → 404 | `RecursoNaoEncontradoException` lançada nos métodos de busca, capturada pelo `GlobalExceptionHandler` |
| Dados inválidos → 400 com detalhes | `GlobalExceptionHandler` captura `MethodArgumentNotValidException` e formata o JSON de erro padrão |

### Operações (Endpoints)

| # | Requisito | Endpoint | HTTP Status |
|---|---|---|---|
| 1 | Registrar aventureiro | `POST /aventureiros` | 201 Created |
| 2 | Listar com filtros e paginação | `GET /aventureiros` | 200 OK |
| 3 | Consultar por ID (com companheiro) | `GET /aventureiros/{id}` | 200 OK |
| 4 | Atualizar nome, classe e nível | `PUT /aventureiros/{id}` | 200 OK |
| 5 | Encerrar vínculo (`ativo = false`) | `DELETE /aventureiros/{id}` | 204 No Content |
| 6 | Recrutar novamente (`ativo = true`) | `PATCH /aventureiros/{id}/recrutar` | 200 OK |
| 7 | Definir ou substituir companheiro | `PUT /aventureiros/{id}/companheiro` | 200 OK |
| 8 | Remover companheiro | `DELETE /aventureiros/{id}/companheiro` | 204 No Content |

### Paginação e Filtros (Listagem)

| Requisito | Implementação |
|---|---|
| Filtro por `classe`, `ativo`, `nivel` (mínimo) | `@RequestParam(required = false)` no `GET /aventureiros` |
| Paginação via `page` (padrão 0) e `size` (padrão 10, máximo 50) | Calculado manualmente com `subList` em `AventureiroController` |
| Header `X-Total-Count` | Total de registros após aplicação dos filtros |
| Header `X-Page` | Número da página atual |
| Header `X-Size` | Tamanho da página utilizado |
| Header `X-Total-Pages` | Total de páginas calculado com `Math.ceil` |
| Lista vazia para página inexistente + headers corretos | Verificação de `fromIndex >= totalElements` retorna `List.of()` com headers normais |
| Ordenação crescente por `id` | `.sorted((a1, a2) -> a1.getId().compareTo(a2.getId()))` no `findByFilters` |
| Listagem sem dados do companheiro | `AventureiroResumoResponse` omite o campo companheiro |

### Padrão de Erro

Todos os erros retornam um JSON consistente no formato:

```json
{
  "mensagem": "Solicitação inválida",
  "detalhes": [
    "classe inválida",
    "nivel deve ser maior ou igual a 1"
  ]
}
```

Implementado em `ErroPadraoResponse` e gerenciado pelo `GlobalExceptionHandler` com `@RestControllerAdvice`.

### Requisitos Técnicos

| Requisito | Implementação |
|---|---|
| Sem banco de dados externo | Dados mantidos em `ArrayList<Aventureiro>` dentro do `AventureiroRepository` (anotado com `@Repository`) |
| ArrayList inicializada com 100 registros | Método `iniciarRegistrosBase()` chamado no construtor do repositório — cria 100 aventureiros: 50 sem companheiro e 50 com companheiro, com variação de classes, espécies e níveis |
| Semântica HTTP correta | `201` para criação, `204` para operações sem corpo, `400` para inválidos, `404` para não encontrado |
| Repositório público no GitHub | ✅ Disponível em: _(link após push)_ |

---

## 🚀 Como Executar

### Pré-requisitos
- **Java 17+** (testado com Java 25)
- **Maven 3.6+**

### Executar localmente
```bash
# Na pasta raiz do projeto (onde está o pom.xml):
mvn spring-boot:run
```

A API sobe em: `http://localhost:8080`

---

## 🗂️ Estrutura do Projeto

```
src/main/java/br/com/guilda/aventureiros/
├── GuildaAventureirosApplication.java  # Ponto de entrada do Spring Boot
├── controller/
│   └── AventureiroController.java      # Todos os 8 endpoints REST
├── domain/
│   ├── Aventureiro.java                # Modelo principal
│   ├── Companheiro.java                # Composição do Aventureiro
│   ├── Classe.java                     # Enum de classes permitidas
│   └── Especie.java                    # Enum de espécies permitidas
├── dto/
│   ├── AventureiroRequest.java         # Entrada: registrar/atualizar aventureiro
│   ├── AventureiroResponse.java        # Saída: dados completos (com companheiro)
│   ├── AventureiroResumoResponse.java  # Saída: listagem resumida (sem companheiro)
│   └── CompanheiroRequest.java         # Entrada: definir/substituir companheiro
├── exception/
│   ├── ErroPadraoResponse.java         # Formato padrão do JSON de erro
│   ├── GlobalExceptionHandler.java     # Interceptador global de erros
│   └── RecursoNaoEncontradoException.java # Exceção de 404
└── repository/
    └── AventureiroRepository.java      # Mock: ArrayList com 100 registros iniciais
```

---

## 📬 Testando com Postman

Importe o arquivo `GuildaAventureiros.postman_collection.json` (na raiz do projeto) no Postman para ter todos os 8 endpoints já configurados com exemplos de requisições válidas e inválidas.

---

## 📌 Observações sobre Escalabilidade (TP2/TP3)

Este projeto (TP1) usa **persistência em memória** propositalmente. Nas próximas versões:
- **TP2**: A `ArrayList` será substituída pelo **Elasticsearch** como motor de busca e persistência.
- **TP3**: O ambiente será orquestrado com **Docker Compose**, facilitando a execução padronizada da API + Elasticsearch com um único comando.
