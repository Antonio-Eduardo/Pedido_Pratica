package Main;

import Services.implement.ServicoCaixaDefault;
import Services.implement.ServicoCaixaRapido;
import Application.ServicoValidacao;
import Application.ServicoCliente;
import Application.ServicoPedido;
import Application.ServicoProduto;
import entities.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Programa {
    public static void main(String[] args) {
        List<Cliente> clientes = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        ServicoCliente servicoCliente = new ServicoCliente();
        ServicoProduto servicoProduto = new ServicoProduto();
        ServicoPedido servicoPedido = new ServicoPedido();
        int resposta = 0;
        while (resposta != 2) {
            resposta = ServicoValidacao.lerInteiros(sc, "Deseja registrar um cliente? [1-SIM|2-NAO]\n");
            switch (resposta) {
                case 1:

                    String nome = ServicoValidacao.lerString(sc, "Nome do cliente: ");
                    String cpf = ServicoValidacao.lerString(sc, "Digite o cpf do cliente: ");
                    String email = ServicoValidacao.lerString(sc, "E-mail do cliente: ");
                    LocalDate dataNasc = ServicoValidacao.lerData(sc,"Data de nascimento do cliente: ");
                    Cliente cliente = servicoCliente.registrarCliente(nome, email, dataNasc, cpf);
                    clientes.add(cliente);
                    break;
                case 2:
                    clientes.sort(Comparator.comparing(Cliente::getNome));
                    System.out.print("\nSaindo do cadastro...\n");
                    break;
                default:
                    System.out.print("Escolha invalida!");
            }
        }
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado, encerrando o programa");
            sc.close();
            return;
        }
        int resposta2 = ServicoValidacao.lerInteiros(sc, "Deseja iniciar o seu pedido? [1-SIM|2-NAO]\n");
        if (resposta2 != 1) {
            System.out.println("Programa encerrado!");
            sc.close();
            return;
        }
        int resposta3 = 0;
        int resposta4 = 0;
        while (resposta4 != 3) {
            resposta4 = ServicoValidacao.lerInteiros(sc,"[1-PROSSEGUIR COM PEDIDO|2-FINALIZAR OPERACAO\n");
            if (resposta4 != 1 && resposta4 != 2){System.out.println("Entrada invalida");}
            if (resposta4 == 2){break;}
            String cpf = ServicoValidacao.lerString(sc, "Digite o CPF da conta que voce deseja realizar a operacao: ");
            Cliente clienteBusca = servicoCliente.buscarCliente(clientes, cpf);
            Pedido pedidoAtual = servicoPedido.criarPedido(clienteBusca);
            resposta3=0;
            while (resposta3 != 3) {
                resposta3 = ServicoValidacao.lerInteiros(sc, "[1-ADICIONAR ITENS|2-FECHAR O PEDIDO|3-SAIR\n");
                switch (resposta3) {
                    case 1:
                        String produto = ServicoValidacao.lerString(sc, "Nome do produto: ");
                        double precoProduto = ServicoValidacao.lerDouble(sc, "Preco do produto: ");
                        Produto prod = servicoProduto.criarProduto(produto, precoProduto);

                        double precoVenda = ServicoValidacao.lerDouble(sc, "Preco de venda: ");
                        int quantidade = ServicoValidacao.lerInteiros(sc, "Quantidade desse produto: ");
                        ItensPedido item = new ItensPedido(quantidade, precoVenda, prod);
                            System.out.println("Pedido criado: " + pedidoAtual.getIdPedido());
                            servicoCliente.associarCliente(clienteBusca, pedidoAtual);
                        servicoPedido.adicionarItem(pedidoAtual, item);
                        break;
                    case 2:
                        String idPedido = ServicoValidacao.lerString(sc, "Digite o codigo do produto: ");
                        UUID id = UUID.fromString(idPedido);
                        Pedido pedidoBusca = servicoPedido.buscarPedido(clienteBusca.getPedidos(), id);
                        servicoPedido.fecharPedido(id,clienteBusca);
                        System.out.println("Pedido finalizado!");

                        System.out.print(pedidoBusca);
                        break;
                    case 3:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.print("Opcao invalida!");
                }
            }
        }
        String cpf = ServicoValidacao.lerString(sc, "Identifique o cliente pelo [CPF]: ");
        Cliente clienteProcura = servicoCliente.buscarCliente(clientes, cpf);

        int caixa = ServicoValidacao.lerInteiros(sc, "Qual caixa deseja usar? [1-CAIXA | 2-CAIXA RAPIDO]\n");
        switch (caixa) {
            case 1:
                ServicoCaixaDefault caixaP = new ServicoCaixaDefault();
                cpf = ServicoValidacao.lerString(sc, "Identifique o cliente pelo [CPF]: ");
                clienteProcura = servicoCliente.buscarCliente(clientes, cpf);
                caixaP.processarPedido(servicoPedido.getPedidos(clienteProcura));
                break;
            case 2:
                ServicoCaixaRapido caixaR = new ServicoCaixaRapido();
                cpf = ServicoValidacao.lerString(sc, "Identifique o cliente pelo [CPF]: ");
                clienteProcura = servicoCliente.buscarCliente(clientes, cpf);
                caixaR.processarPedido(servicoPedido.getPedidos(clienteProcura));
                break;
        }
    }
}

