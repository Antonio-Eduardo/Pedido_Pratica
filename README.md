#Sistema de pedidos em Java

Projeto desenvolvido em Java para simular um sistema de pedidos com foco em orientação a objetos e separação de responsabilidades.

##Funcionalidades

- Cadastro de clientes
- Criação de pedidos vinculados a clientes
- Adição de itens ao pedido
- Cálculo automático do valor total do pedido
- Registro de data e status do pedido
- Busca de cliente por CPF
- Busca de pedido por id do pedido gerado por UUID
- Arquivar todo cliente com seus dados a partir do momento que ele é gerado
- Associação de pedidos ao cliente

##Conceitos aplicados

- Programação Orientada a Objetos (POO)
- Separação em camadas (Entities e services)
- Enum para controle de status do pedido
- Stream API para busca de dados
- Manipulação de datas com LocalDate
- Encapsulamento e organização de código
- Uso de generics pra aumentar a flexibilidade da minha interface de dados

##Estrutura do projeto

- `entities` → classes principais (Cliente, Pedido, Produto, ItensPedido)
- `services` → Interfaces
- `Implements`→ Serviços que implementam a interface
- `Repository`→ Salvar dados
- `Main.Main` → execução via console
- `Excecoes` → Exceções a regra do negocio
- `ENUM`→ estados fixos do sistema

##Regras de negócio

- Cada pedido pode conter vários itens
- O valor total é calculado automaticamente
- O pedido só é fechado após cálculo e definição de status
- Cliente pode possuir múltiplos pedidos
- Enum usado como identificador para qualquer ação que fuja da regra do negocio.
- Caixa rapido tem um limite de itens até 15 itens.

##Exemplo de status

- PAGAMENTO_PENDENTE
- PROCESSANDO
-  FINALIZADO

##Exemplo do Enum RegraServico

- CAIXA_LIMITE_EXCEDIDO

##Implementação do banco de dados usando SQL/MySQL com JDBC (EM ANDAMENTO)
-- adicionando classe de conexão JDBC e db.properties

##Como executar

1. Abrir o projeto no IntelliJ
2. Rodar a classe `Main.Main`