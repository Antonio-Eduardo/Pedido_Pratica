package dao;

import entities.Produto;

public interface ProdutoDAO {
    void salvar(Produto p);
    Produto buscarProduto(Produto p);
    void deletarProduto(Produto p);
    void updateProduto(Produto p);
}
