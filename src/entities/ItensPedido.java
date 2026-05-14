package entities;

public class ItensPedido {
    private Long iD;
    private Integer quantidade;
    private double preco;
    private Long idProduto;
    private Long idPedido;

    public ItensPedido() {
    }
    public ItensPedido(Integer quantidade, double preco, Long idProduto, Long idPedido) {
        this.quantidade = quantidade;
        this.preco = preco;
        this.idProduto = idProduto;
        this.idPedido = idPedido;
    }

    public ItensPedido(Long iD, Integer quantidade, double preco, Long idProduto, Long idPedido) {
        this.iD = iD;
        this.quantidade = quantidade;
        this.preco = preco;
        this.idProduto = idProduto;
        this.idPedido = idPedido;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public double getPreco() {
        return preco;
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
        sb.append("iD do produto: ").append(idProduto);
        sb.append(String.format("%n%.2f", preco)).append(" R$ ");
        sb.append("Quantidade: ").append(quantidade).append("\n");

        return sb.toString();
    }
}
