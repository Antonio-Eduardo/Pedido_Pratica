package dao;

import entities.Produto;

public interface ProdutoDAO {
    void salvar(Produto p);
    Produto buscarProdutoPorId(Long id);
    void deletarProdutoPorId(Long id);
}
