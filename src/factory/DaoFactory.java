package factory;

import dao.ClienteDAO;
import dao.ProdutoDAO;
import dao.impl.ClienteDaoImpl;
import dao.impl.ProdutoDaoImpl;

public interface DaoFactory {
    public static ClienteDAO criarClienteDao(){return new ClienteDaoImpl();}
    public static ProdutoDAO criarProdutoDao(){return new ProdutoDaoImpl();}
}
