package Services;

import entities.Cliente;
import entities.Pedido;

import java.util.List;

public interface PersistenciaDdados {
    public void salvar(Cliente cliente);
    List<Cliente> listar(Cliente cliente);
}
