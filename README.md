Esse foi o meu segundo projeto visando praticar enumeração e composição, o terceiro já foi publicado, esse sistema simula o fluxo básico de um pedido de compra, desde os dados do cliente até o processamento dos itens e cálculo do valor total.
Um pouco do que eu utilizei:

    Composição de Objetos: A classe Pedido contém uma lista de ItensPedido, e cada item está associado a um Produto.

    Enumerações: Utilizei enums para controlar os estados do pedido (PAGAMENTO_PENDENTE, PROCESSANDO, etc).

    StringBuilder: Para otimizar a geração do relatório final no método toString.

    Manipulação de Datas: Uso de SimpleDateFormat para formatar a entrada e saída de datas.

    Encapsulamento: Todos os atributos estão protegidos, sendo acessados via getters e setters.


    Cliente: Armazena nome, e-mail e data de nascimento.

    Produto: Define o nome e o preço base do produto.

    ItensPedido: Representa a linha do pedido (une o produto com a quantidade e o preço de venda). Possui o método subTotalPedido().

    Pedido: A classe central que gerencia o cliente, o status e a lista de itens. Possui o método totaL() para somar o fechamento do carrinho.

    StatusPedido: Enum que define as etapas de processamento.
Esse por ter sido meu segundo demandou mais tempo que o terceiro, mas foi nesse projeto que comecei a entender
como os objetos se conectam.