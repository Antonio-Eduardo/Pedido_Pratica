package entities;

import java.util.HashMap;
import java.util.Map;

public class Produto {
    private String iD;
    private String nome;
    private double precoProduto;

    public Produto() {
    }
    public Produto(String iD, String nome, double precoProduto) {
        this.nome = nome;
        this.precoProduto = precoProduto;
        this.iD = iD;
    }

    public double getPreco() {
        return precoProduto;
    }

    public void setPreco(double preco) {
        this.precoProduto = preco;
    }

    public String getiD() {
        return iD;
    }

    public void setiD(String iD) {
        this.iD = iD;
    }

    private static long contador = 1;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
