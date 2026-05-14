package dao;

import entities.Cliente;
import entities.Pedido;

public interface PedidoDAO {
    void salvar(Cliente c , Pedido p);
    Pedido buscarPedidoPorId(Long id);
    void deletarPedidoPorId(Long id);
}
