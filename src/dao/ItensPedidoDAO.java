package dao;

import entities.ItensPedido;
import entities.Pedido;
import entities.Produto;

public interface ItensPedidoDAO {
    void salvar(Produto prod, Pedido ped, ItensPedido itensPedido);
    ItensPedido buscarItens(Long id);
    void deleteItem(Long id);
}
