package dao;

import entities.Cliente;
import entities.Pedido;

public interface PedidoDAO {
    void salvar(Cliente c , Pedido p);
    Pedido buscarPedidoPorId(Long id, Cliente cliente);
    void deletarPedidoPorId(Long id);
    void updatePedido(Pedido pedido);
}
