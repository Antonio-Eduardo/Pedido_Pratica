package entities;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido {
    private Date momentoDaCompra;
    private StatusPedido status;
    private Cliente cliente;
    private List<ItensPedido> itensPedidos = new ArrayList<>();

    public Pedido(){}

    public Pedido(Date momentoDaCompra, StatusPedido status, Cliente cliente) {
        this.momentoDaCompra = momentoDaCompra;
        this.status = status;
        this.cliente = cliente;
    }
    public void addItem(ItensPedido item){
        itensPedidos.add(item);
    }
    public void removeItem(ItensPedido item){
        itensPedidos.remove(item);
    }
    public double totaL(){
        double soma = 0;
        for (ItensPedido c : itensPedidos){
            soma += c.subTotalPedido();
        }
        return soma;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        StringBuilder sb = new StringBuilder();

        sb.append("\n--- RESUMO DO PEDIDO ---\n");
        sb.append("Instante do Pedido: ").append(sdf.format(momentoDaCompra)).append("\n");
        sb.append("Status: ").append(status).append("\n");
        sb.append("Cliente: ").append(cliente.getNome()).append(" (").append(cliente.getEmail()).append(")\n");

        sb.append("\nItens do Pedido:\n");
        for (ItensPedido x : itensPedidos) {
            sb.append(x); // Aqui ele já traz a linha formatada lá de cima
        }

        sb.append("\nVALOR TOTAL: R$ ").append(String.format("%.2f", totaL()));

        return sb.toString();
    }
}
