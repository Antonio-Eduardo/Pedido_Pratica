#Sistema de pedidos em Java

Projeto desenvolvido em Java para simular um sistema de pedidos com foco em orientação a objetos e separação de responsabilidades.

##Funcionalidades

- Cadastro de clientes
- Criação de pedidos vinculados a clientes
- Adição de itens ao pedido
- Cálculo automático do valor total do pedido
- Registro de data e status do pedido
- Busca de cliente por CPF
- Associação de pedidos ao cliente

##Conceitos aplicados

- Programação Orientada a Objetos (POO)
- Separação em camadas (Entities e Services)
- Enum para controle de status do pedido
- Stream API para busca de dados
- Manipulação de datas com LocalDate
- Encapsulamento e organização de código

##Estrutura do projeto

- `entities` → classes principais (Cliente, Pedido, Produto, ItensPedido, StatusPedido)
- `PedidoServicos` → regras de negócio e serviços
- `Programa` → execução via console

##Regras de negócio

- Cada pedido pode conter vários itens
- O valor total é calculado automaticamente
- O pedido só é fechado após cálculo e definição de status
- Cliente pode possuir múltiplos pedidos

##Exemplo de status

- PAGAMENTO_PENDENTE
- PROCESSANDO
- ENVIADO
- ENTREGUE

##Como executar

1. Abrir o projeto no IntelliJ
2. Rodar a classe `Programa`