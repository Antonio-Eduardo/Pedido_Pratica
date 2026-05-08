package PedidoServicos;

import entities.Cliente;
import entities.Pedido;
import java.time.LocalDate;
import java.util.List;

public class ServicoCliente {
    public Cliente registrarCliente(String nome, String email, LocalDate dataDeNascimento, String cpf) {
        return new Cliente(nome, email, dataDeNascimento, cpf);
    }
    public Cliente buscarCliente(List<Cliente> clientes, String cpf){
        return clientes.stream().filter(c -> c.getCpf().equals(cpf)).findFirst().orElse(null);
    }
    public void associarCliente(Cliente cliente, Pedido pedido){
        cliente.addPedido(pedido);
    }
}
