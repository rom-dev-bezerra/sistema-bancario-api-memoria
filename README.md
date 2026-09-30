# Sistema Bancário API (Armazenamento em Memória) ☕🚀

API RESTful que simula o ecossistema de um banco digital core, desenvolvida para fins de estudo dos fundamentos do ecossistema **Java** e **Spring Boot**. 

Este projeto marca a evolução de um sistema originalmente construído em modo console (CLI) para uma arquitetura Web robusta e desacoplada, servindo dados estruturados em tempo real para o cliente.

## 🛠️ Tecnologias e Ferramentas Utilizadas
* **Java** (Conceitos avançados de Orientação a Objetos, Encapsulamento, Coleções e Fluxos)
* **Spring Boot 3.x** (Módulos Web e inversão de controle)
* **Maven** (Gerenciamento de dependências e automação de escopo)
* **IntelliJ IDEA** & **Talend API Tester** (Desenvolvimento e testes de endpoints)

## 🧱 Arquitetura e Padrões Aplicados
Durante o desenvolvimento, apliquei conceitos fundamentais de desenvolvimento backend exigidos pelo mercado:
* **Desacoplamento de Interface:** Toda a lógica transacional foi isolada de saídas de console (`System.out.println`), utilizando controle de fluxo moderno baseado em retornos lógicos (`boolean`) e tratamento de exceções.
* **Mapeamento RESTful Semântico:** Utilização correta dos verbos HTTP (`GET` para leitura e `POST` para mutação e operações financeiras).
* **Tratamento de Erros:** Manipulação manual de respostas utilizando `ResponseStatusException` para garantir que rotas inválidas ou contas inexistentes retornem o código oficial **Status 404 (Not Found)** em vez de páginas em branco ou erros internos.
* **Serialização Jackson:** Conversão automática de objetos complexos e coleções Java em strings estruturadas no padrão **JSON**.

## 🌐 Endpoints Mapeados da API

### 1. Boas-Vindas
* **Rota:** `GET /boas-vindas`
* **Descrição:** Verifica a saúde e a inicialização bem-sucedida do servidor Tomcat.

### 2. Listar Todas as Contas
* **Rota:** `GET /contas`
* **Descrição:** Retorna um Array de JSON contendo a listagem completa de todas as contas cadastradas na memória.

### 3. Buscar Conta Específica
* **Rota:** `GET /contas/{numero}`
* **Descrição:** Busca uma conta filtrando pelo parâmetro dinâmico da URL. Caso não exista, retorna **404 Not Found**.

### 4. Realizar Depósito
* **Rota:** `POST /contas/{numero}/depositar?valor={quantidade}`
* **Descrição:** Executa a entrada de fundos na conta selecionada e atualiza o saldo de forma dinâmica na memória do servidor.

### 5. Realizar Saque
* **Rota:** `POST /contas/{numero}/sacar?valor={quantidade}`
* **Descrição:** Realiza a validação de fundos e executa o débito do saldo se a transação for permitida.

### 6. Transferência entre Contas
* **Rota:** `POST /contas/{numero}/transferir?contaDestino={numeroDestino}&valor={quantidade}`
* **Descrição:** Rota transacional complexa que executa duas buscas simultâneas na coleção, valida ambas as entidades e atualiza de forma síncrona o débito e o crédito dos envolvidos.

---
*Este projeto representa a Fase 1 da minha jornada backend. A próxima etapa consistirá na replicação desta lógica acoplada ao banco de dados relacional **PostgreSQL** utilizando Spring Data JPA.*
