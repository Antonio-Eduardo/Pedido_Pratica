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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Programa {
    public static void main(String[] args) {
        List<Cliente> clientes = new ArrayList<>();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Scanner sc = new Scanner(System.in);
        ServicoCliente servicoCliente = new ServicoCliente();
        ServicoProduto servicoProduto = new ServicoProduto();
        int resposta = 0;
        while (resposta != 2) {
            resposta = ServicoValidacao.lerInteiros(sc, "Deseja registrar um cliente? [1-SIM|2-NAO]\n");
            switch (resposta) {
                case 1:

                    String nome = ServicoValidacao.lerString(sc, "Nome do cliente: ");
                    String cpf = ServicoValidacao.lerString(sc, "Digite o cpf do cliente: ");
                    String email = ServicoValidacao.lerString(sc, "E-mail do cliente: ");
                    String dataNascString = ServicoValidacao.lerString(sc, "Data de nascimento do cliente: ");
                    LocalDate dataFormatada = LocalDate.parse(dataNascString, fmt);
                    Cliente cliente = servicoCliente.registrarCliente(nome, email, dataFormatada, cpf);
                    clientes.add(cliente);
                    break;
                case 2:
                    System.out.print("\nSaindo do cadastro...");
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
        ServicoPedido servicoPedido = new ServicoPedido();
        int resposta3 = 0;
        Pedido pedidoAtual = null;
        while (resposta3 != 3) {
            String cpf = ServicoValidacao.lerString(sc, "Digite o CPF da conta que voce deseja realizar a operacao: ");
            Cliente clienteBusca = servicoCliente.buscarCliente(clientes, cpf);
            resposta3 = ServicoValidacao.lerInteiros(sc, "[1-ADICIONAR ITENS|2-FECHAR O PEDIDO|3-SAIR\n");
            switch (resposta3) {
                case 1:
                    String produto = ServicoValidacao.lerString(sc, "Nome do produto: ");
                    double precoProduto = ServicoValidacao.lerDouble(sc, "Preco do produto: ");
                    Produto prod = servicoProduto.criarProduto(produto, precoProduto);

                    double precoVenda = ServicoValidacao.lerDouble(sc, "Preco de venda: ");
                    int quantidade = ServicoValidacao.lerInteiros(sc, "Quantidade desse produto: ");
                    ItensPedido item = new ItensPedido(quantidade, precoVenda, prod);
                    if (pedidoAtual == null) {
                        pedidoAtual = new Pedido(clienteBusca);
                        System.out.println("Pedido criado: " + pedidoAtual.getIdPedido());
                        servicoCliente.associarCliente(clienteBusca, pedidoAtual);
                    }
                    servicoPedido.adicionarItem(pedidoAtual, item);
                    break;


                case 2:
                    String idPedido = ServicoValidacao.lerString(sc, "Digite o codigo do produto: ");
                    UUID id = UUID.fromString(idPedido);
                    Pedido pedidoBusca = servicoPedido.buscarPedido(clienteBusca.getPedidos(), id);
                    servicoPedido.fecharPedido(clienteBusca);
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

