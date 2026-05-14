package entities;

import enums.StatusPedido;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Pedido{
    private Long idPedido;
    private StatusPedido status;
    private Timestamp dataHora;
    private Long idCliente;
    private double precoPedido;
    private List<ItensPedido> itensPedidos = new ArrayList<>();

    public Pedido(){}

    public Pedido(Long idPedido, Long idCliente, double precoPedido, Timestamp dataHora, StatusPedido status) {
        this.idPedido = idPedido;
        this.idCliente = idCliente;
        this.precoPedido = precoPedido;
        this.dataHora = dataHora;
        this.status = status;
    }

    public Pedido(Long idCliente, double precoPedido, Timestamp dataHora, StatusPedido status) {
        this.idCliente = idCliente;
        this.precoPedido = precoPedido;
        this.dataHora = dataHora;
        this.status = status;
    }

    public Pedido(Long idCliente, StatusPedido status) {
        this.idCliente = idCliente;
        this.status = status;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Timestamp getDataHora() {
        return dataHora;
    }

    public void setDataHora(Timestamp dataHora) {
        this.dataHora = dataHora;
    }

    public void addItem(ItensPedido item){
        itensPedidos.add(item);
    }
    public void removeItem(ItensPedido item){
        itensPedidos.remove(item);
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



    public void finalizarPedido(){
        if (itensPedidos.isEmpty()){
            System.out.println("pedido vazio");
        }
        this.status = StatusPedido.FINALIZADO;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        StringBuilder sb = new StringBuilder();

        sb.append("\n--- RESUMO DO PEDIDO ---\n");
        if (dataHora != null) {
            sb.append("Instante do Pedido: ").append(dataHora).append("\n");
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
