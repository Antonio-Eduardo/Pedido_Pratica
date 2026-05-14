package application;

import dao.ProdutoDAO;
import entities.Produto;
import factory.DaoFactory;

import java.util.HashMap;
import java.util.Map;

public class ServicoProduto {
    ProdutoDAO produtoDAO = DaoFactory.criarProdutoDao();
    public Produto criarProduto(String nome, double preco){
        Produto p = new Produto(nome,preco);
        produtoDAO.salvar(p);
        return p;
    }

}
