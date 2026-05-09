package entities;

import ENUM.StatusPedido;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Pedido{
    private Cliente cliente;
    private UUID idPedido;
    private String cpf;
    private LocalDateTime momentoDaCompra;
    private StatusPedido status;
    private double precoPedido;
    private List<ItensPedido> itensPedidos = new ArrayList<>();

    public Pedido(){}

    public Pedido(Cliente cliente) {
        this.status = StatusPedido.PROCESSANDO;
        this.cliente = cliente;
        this.cpf = cliente.getCpf();
    }
    public void addItem(ItensPedido item){
        itensPedidos.add(item);
    }
    public void removeItem(ItensPedido item){
        itensPedidos.remove(item);
    }
    public LocalDateTime getMomentoDaCompra() {
        return momentoDaCompra;
    }

    public void setMomentoDaCompra(LocalDateTime momentoDaCompra) {
        this.momentoDaCompra = momentoDaCompra;
    }
    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public List<ItensPedido> getItensPedidos() {
        return itensPedidos;
    }

    public void setItensPedidos(List<ItensPedido> itensPedidos) {
        this.itensPedidos = itensPedidos;
    }

    public double getPrecoPedido() {
        return precoPedido;
    }

    public void setPrecoPedido(double precoPedido) {
        this.precoPedido = precoPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void finalizarPedido(){
        if (itensPedidos.isEmpty()){
            System.out.println("pedido vazio");
        }
        this.status = StatusPedido.FINALIZADO;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public UUID getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(UUID idPedido) {
        this.idPedido = idPedido;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        StringBuilder sb = new StringBuilder();

        sb.append("\n--- RESUMO DO PEDIDO ---\n");
        if (momentoDaCompra != null) {
            sb.append("Instante do Pedido: ").append(momentoDaCompra.format(fmt)).append("\n");
        } else {
            sb.append("SEM DATA");
        }
        sb.append("Status: ").append(status).append("\n");
        sb.append("\nItens do Pedido:\n");

        for (ItensPedido x : itensPedidos){
            sb.append(x.toString());
        }

        sb.append("\nTotal do Pedido: R$ ").append(String.format("%.2f", precoPedido)).append("\n");
        sb.append("========================\n");

        return sb.toString();
    }
}
