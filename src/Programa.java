import CaixaServico.CaixaPadrao;
import CaixaServico.CaixaRapido;
import Excecoes.EntradasExceptions;
import PedidoServicos.ServicoCliente;
import PedidoServicos.ServicoPedido;
import PedidoServicos.ServicoProduto;
import entities.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Programa {
    public static void main(String[] args) {
        List<Cliente> clientes = new ArrayList<>();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Scanner sc = new Scanner(System.in);
        ServicoCliente servicoCliente = new ServicoCliente();
        ServicoProduto servicoProduto = new ServicoProduto();
        int resposta = 0;
        while (resposta != 2) {
            resposta = EntradasExceptions.lerInteiros(sc, "Deseja registrar um cliente? [1-SIM|2-NAO]\n");
            switch (resposta) {
                case 1:

                    String nome = EntradasExceptions.lerString(sc, "Nome do cliente: ");
                    String cpf = EntradasExceptions.lerString(sc, "Digite o cpf do cliente: ");
                    String email = EntradasExceptions.lerString(sc, "E-mail do cliente: ");
                    String dataNascString = EntradasExceptions.lerString(sc, "Data de nascimento do cliente: ");
                    LocalDate dataFormatada = LocalDate.parse(dataNascString, fmt);
                    Cliente cliente = servicoCliente.registrarCliente(nome, email, dataFormatada, cpf);
                    clientes.add(cliente);
                    break;
                case 2:
                    System.out.print("Saindo do cadastro...");
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
        int resposta2 = EntradasExceptions.lerInteiros(sc, "Deseja iniciar o seu pedido? [1-SIM|2-NAO]\n");
        if (resposta2 != 1) {
            System.out.println("Programa encerrado!");
            sc.close();
            return;
        }
        String cpf = EntradasExceptions.lerString(sc, "Digite o CPF da conta que voce deseja abrir o pedido: ");

        Cliente clienteBusca = servicoCliente.buscarCliente(clientes, cpf);

        if (clienteBusca == null) {
            System.out.print("Cliente com CPF " + cpf + " nao encontrado!");
            System.out.print("Encerrando programa...");
            sc.close();
            return;
        }
        ServicoPedido servicoPedido = new ServicoPedido();
        Pedido pedido = servicoPedido.criarPedido();
        int resposta3 = 0;

        while (resposta3 != 3) {
            resposta3 = EntradasExceptions.lerInteiros(sc, "[1-ADICIONAR ITENS|2-FECHAR O PEDIDO|3-SAIR\n");
            switch (resposta3) {
                case 1:
                    String produto = EntradasExceptions.lerString(sc, "Nome do produto: ");
                    double precoProduto = EntradasExceptions.lerDouble(sc, "Preco do produto: ");
                    Produto prod = servicoProduto.criarProduto(produto,precoProduto);

                    double precoVenda = EntradasExceptions.lerDouble(sc, "Preco de venda: ");
                    int quantidade = EntradasExceptions.lerInteiros(sc, "Quantidade desse produto: ");
                    ItensPedido item = new ItensPedido(quantidade, precoVenda, prod);

                    servicoPedido.adicionarItem(pedido, item);
                    break;

                case 2:
                    servicoPedido.fecharPedido(pedido);
                    System.out.println("Pedido finalizado!");
                    System.out.print(pedido);
                    servicoCliente.associarCliente(clienteBusca, pedido);
                    break;

                case 3:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.print("Opcao invalida!");
            }
        }
        int caixa = EntradasExceptions.lerInteiros(sc,"Qual caixa deseja usar? [1-CAIXA | 2-CAIXA RAPIDO]\n");
        switch (caixa){
            case 1:
                CaixaPadrao caixaP = new CaixaPadrao();
                cpf = EntradasExceptions.lerString(sc,"Identifique o cliente pelo [CPF]: ");
                clienteBusca =servicoCliente.buscarCliente(clientes,cpf);
                if (clienteBusca == null){
                    System.out.println("Cliente nao encontrado");
                    return;
                }
                caixaP.processarPedido(servicoPedido.getPedidos(clienteBusca));
            case 2:
                CaixaRapido caixaR = new CaixaRapido();
                cpf = EntradasExceptions.lerString(sc,"Identifique o cliente pelo [CPF]: ");
                clienteBusca =servicoCliente.buscarCliente(clientes,cpf);
                if (clienteBusca == null){
                    System.out.println("Cliente nao encontrado");
                    return;
                }
                caixaR.processarPedido(servicoPedido.getPedidos(clienteBusca));
        }
        System.out.println(clienteBusca);
        System.out.println(pedido);
    }
}

