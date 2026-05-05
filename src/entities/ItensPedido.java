package entities;

public class ItensPedido {
    private Integer quantidade;
    private double preco;
    private Produto produto;

    public ItensPedido() {
    }
    public ItensPedido(Integer quantidade, double preco, Produto produto) {
        this.quantidade = quantidade;
        this.preco = preco;
        this.produto = produto;
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
        sb.append(produto.getNome());
        sb.append(", R$ ").append(String.format("%.2f", preco));
        sb.append(", Quantidade: ").append(quantidade);
        sb.append("\n");

        return sb.toString();
    }
}
