package Application;

import entities.Cliente;
import entities.Pedido;
import repository.ServicoHistorico;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ServicoCliente {
    ServicoHistorico servicoHistorico = new ServicoHistorico();
    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public Cliente registrarCliente(String nome, String email, LocalDate dataDeNascimento, String cpf) {
        Cliente c = new Cliente(nome, email, dataDeNascimento, cpf);
        servicoHistorico.salvar(c);
        return c;
    }

    public Cliente buscarCliente(List<Cliente> clientes, String cpf) {
        Cliente z = clientes.stream().filter(c -> c.getCpf().equals(cpf)).findFirst().orElse(null);
        if (z == null) {
            System.out.println("CPF nao existe");
        }
        return z;
    }


    public void associarCliente(Cliente cliente, Pedido pedido) {
        cliente.addPedido(pedido);
    }

}
