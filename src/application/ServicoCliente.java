package application;

import dao.ClienteDAO;
import dao.ProdutoDAO;
import entities.Cliente;
import entities.Pedido;
import factory.DaoFactory;

import java.time.LocalDate;
import java.util.List;

public class ServicoCliente {
    ClienteDAO clienteDAO = DaoFactory.criarClienteDao();

    public Cliente registrarCliente(String nome, String email, LocalDate dataDeNascimento, String cpf) {
        Cliente c = new Cliente(nome, email, dataDeNascimento, cpf);
        clienteDAO.salvar(c);
        return c;
    }
}
