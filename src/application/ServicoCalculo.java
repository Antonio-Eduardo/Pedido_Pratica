package application;

import dao.ItensPedidoDAO;
import entities.ItensPedido;
import entities.Pedido;
import factory.DaoFactory;

import java.util.ArrayList;
import java.util.List;

public class ServicoCalculo {
    ItensPedidoDAO itensPedidoDAO = DaoFactory.criarItensPedidoDao();
    public double calcularPedido(Pedido pedido) {
        List<ItensPedido> itensPedidoList = itensPedidoDAO.buscarItensDoPedido(pedido.getIdPedido());
        return itensPedidoList.stream().mapToDouble(p -> p.getPreco() * p.getQuantidade()).sum();
    }
}
