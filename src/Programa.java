import PedidoServicos.ServicoCalculo;
import PedidoServicos.ServicoCliente;
import PedidoServicos.ServicoPedido;
import entities.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Programa {
    public static void main(String[] args) {
        List<Cliente> clientes = new ArrayList<>();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Scanner sc = new Scanner(System.in);
        ServicoCliente servicoCliente = new ServicoCliente();
        int resposta = 0;
        while (resposta != 2) {
            System.out.println("Deseja registrar um cliente? [1-SIM|2-NAO]");
            resposta = sc.nextInt();
            sc.nextLine();
            switch (resposta) {
                case 1:
                    System.out.print("Nome do cliente: ");
                    String nome = sc.nextLine();

                    System.out.print("Digite o cpf do cliente: ");
                    String cpf = sc.nextLine();

                    System.out.print("E-mail do cliente: ");
                    String email = sc.nextLine();

                    System.out.print("Data de nascimento do cliente: ");
                    String dataNascString = sc.nextLine();
                    LocalDate dataFormatada = LocalDate.parse(dataNascString, fmt);
                    Cliente cliente = servicoCliente.registrarCliente(nome, email, dataFormatada, cpf);
                    clientes.add(cliente);
                    break;
                case 2:
                    System.out.println("Saindo do cadastro...");
                    break;
                default:
                    System.out.println("Escolha invalida!");
            }
        }
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado, encerrando o programa");
            sc.close();
            return;
        }
        System.out.print("Deseja iniciar o seu pedido? [1-SIM|2-NAO]");
        int resposta2 = sc.nextInt();
        sc.nextLine();

        if (resposta2 != 1) {
            System.out.println("Programa encerrado!");
            sc.close();
            return;
        }
        System.out.print("Digite o CPF da conta que voce deseja abrir o pedido: ");
        String cpf = sc.nextLine();
        Cliente clienteBusca = servicoCliente.buscarCliente(clientes,cpf);

        if (clienteBusca == null) {
            System.out.println("Cliente com CPF " + cpf + " nao encontrado!");
            System.out.println("Encerrando programa...");
            sc.close();
            return;
        }
        ServicoPedido servicoPedido = new ServicoPedido();
        Pedido pedido = servicoPedido.criarPedido();
        int resposta3 = 0;

        while (resposta3 != 3) {
            System.out.println("[1-ADICIONAR ITENS|2-FECHAR O PEDIDO|3-SAIR");
            resposta3 = sc.nextInt();
            sc.nextLine();
            switch (resposta3) {
                case 1:
                    System.out.println("Nome do produto: ");
                    String produto = sc.nextLine();

                    System.out.println("Preco do produto: ");
                    double precoProduto = sc.nextDouble();

                    Produto prod = new Produto(produto, precoProduto);

                    System.out.println("Preco de venda: ");
                    double precoVenda = sc.nextDouble();

                    System.out.println("Quantidade desse produto: ");
                    int quantidade = sc.nextInt();
                    sc.nextLine();

                    ItensPedido item = new ItensPedido(quantidade, precoVenda, prod);
                    servicoPedido.adicionarItem(pedido,item);
                    break;

                case 2:
                    servicoPedido.fecharPedido(pedido);
                    System.out.println("Pedido finalizado!");
                    System.out.println(pedido);
                    servicoCliente.associarCliente(clienteBusca,pedido);
                    break;

                case 3:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opcao invalida!");
            }
        }
        System.out.println(clienteBusca);
        System.out.println(pedido);
    }
}

