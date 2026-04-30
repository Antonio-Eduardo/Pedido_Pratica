package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ItensPedido {
    private Integer quantidade;
    private double preco;
    private Produto produto;

    public ItensPedido() {
    }
    public ItensPedido(Integer quantidade, double precoTotal, Produto produto) {
        this.quantidade = quantidade;
        this.preco = precoTotal;
        this.produto = produto;
    }
    public double subTotalPedido() {
        return preco * quantidade;
    }

    public double getPreco() {
        return preco;
    }
    public Produto getProduto() {
        return produto;
    }
    public Integer getQuantidade() {
        return quantidade;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(produto);
        sb.append(", R$ ").append(String.format("%.2f", preco));
        sb.append(", Quantidade: ").append(quantidade);
        sb.append(", Subtotal: R$ ").append(String.format("%.2f", subTotalPedido()));
        sb.append("\n");

        return sb.toString();
    }
}
