package factory;

import dao.ClienteDAO;
import dao.ItensPedidoDAO;
import dao.PedidoDAO;
import dao.ProdutoDAO;
import dao.impl.ClienteDaoImpl;
import dao.impl.ItensPedidoDaoImpl;
import dao.impl.PedidoDaoImpl;
import dao.impl.ProdutoDaoImpl;

public interface DaoFactory {
    public static ClienteDAO criarClienteDao() {
        return new ClienteDaoImpl();
    }

    public static ProdutoDAO criarProdutoDao() {
        return new ProdutoDaoImpl();
    }

    public static PedidoDAO criarPedidoDao() {
        return new PedidoDaoImpl();
    }

    public static ItensPedidoDAO criarItensPedidoDao() {
        return new ItensPedidoDaoImpl();
    }
}
