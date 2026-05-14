package entities;

public class ItensPedido {
    private Long iD;
    private Integer quantidade;
    private double preco;
    private Produto produto;
    private Long idProduto;
    private Long idPedido;

    public ItensPedido() {
    }
    public ItensPedido(Integer quantidade, double preco, Produto produto) {
        this.quantidade = quantidade;
        this.preco = preco;
        this.produto = produto;
    }

    public ItensPedido(Long iD, Integer quantidade, double preco, Long idProduto, Long idPedido) {
        this.iD = iD;
        this.quantidade = quantidade;
        this.preco = preco;
        this.idProduto = idProduto;
        this.idPedido = idPedido;
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

    public Long getiD() {
        return iD;
    }

    public void setiD(Long iD) {
        this.iD = iD;
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
