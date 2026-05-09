package Services;

import entities.Cliente;
import entities.Pedido;

import java.util.List;

public interface PersistenciaDdados {
    public void salvar(Pedido pedido);
    List<Pedido> listar(Cliente cliente);
}
