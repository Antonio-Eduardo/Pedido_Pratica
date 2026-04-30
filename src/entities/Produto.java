package entities;

public class Produto {
    private String nome;
    private double precoProduto;

    public Produto(){}

    public Produto(String nome, double precoProduto) {
        this.nome = nome;
        this.precoProduto = precoProduto;
    }

    public double getPreco() {
        return precoProduto;
    }

    public void setPreco(double preco) {
        this.precoProduto = preco;
    }

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
