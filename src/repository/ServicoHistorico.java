package repository;

import Services.PersistenciaDdados;
import entities.Cliente;
import entities.Pedido;

import java.util.List;

public class ServicoHistorico implements PersistenciaDdados {
    private static final String FILE = "clientes.txt";

    @Override
    public List<Pedido> listar(Cliente cliente) {
        return List.of();
    }
    @Override
    public void salvar(Pedido pedido) {

    }
}
