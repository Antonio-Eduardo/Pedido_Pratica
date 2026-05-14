package dao;

import entities.ItensPedido;
import entities.Pedido;
import entities.Produto;

import java.util.List;

public interface ItensPedidoDAO {
    void salvar(Produto prod, Pedido ped, ItensPedido itensPedido);
    public List<ItensPedido> buscarItensDoPedido(Long id);
    void deleteItem(Long id);
}
