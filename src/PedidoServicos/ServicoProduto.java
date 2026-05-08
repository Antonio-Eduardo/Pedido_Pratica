package PedidoServicos;

import entities.Produto;
import java.util.HashMap;
import java.util.Map;

public class ServicoProduto {
    private long contador = 1;
    Map<String, Produto> produtos = new HashMap<>();

    public Produto criarProduto(String nome, double preco){
        String id = "PROD-" + contador++;
        Produto p = new Produto(id,nome,preco);
        produtos.put(p.getiD(),p);
        return p;
    }
    public Produto buscarProduto(String id){
        return produtos.get(id);
    }
}
