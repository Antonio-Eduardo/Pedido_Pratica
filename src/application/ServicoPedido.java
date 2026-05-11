package application;

import entities.Cliente;
import entities.ItensPedido;
import entities.Pedido;
import ENUM.StatusPedido;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ServicoPedido {

    public Pedido criarPedido() {
        return new Pedido();
    }

    public void adicionarItem(Pedido pedido, ItensPedido item) {
        pedido.addItem(item);
    }
    private final ServicoCalculo calculo = new ServicoCalculo();

    public void fecharPedido(UUID id,Cliente cliente) {
        Pedido p = buscarPedido(cliente.getPedidos(),id);
        double total =calculo.calcularPedido(p);
        p.setMomentoDaCompra(LocalDateTime.now());
        p.setStatus(StatusPedido.PAGAMENTO_PENDENTE);
        p.setPrecoPedido(total);

    }

    public Pedido getPedidos(Cliente cliente) {
        Pedido c = cliente.getPedidos().stream().filter(x -> x.getStatus() == StatusPedido.PAGAMENTO_PENDENTE).findFirst().orElse(null);
        if (c == null) {
            System.out.println("vazio");
            return null;
        }
        return c;
    }
    public Pedido criarPedido(Cliente cliente){
        Pedido pedido = new Pedido();
        pedido.setIdPedido(UUID.randomUUID());
        System.out.println("Codigo do pedido: " + pedido.getIdPedido());
        cliente.getPedidos().add(pedido);
        return pedido;
    }

    public Pedido buscarPedido(List<Pedido> pedidos, UUID codigo) {
        Pedido z = pedidos.stream().filter(c -> c.getIdPedido().equals(codigo)).findFirst().orElse(null);
        if (z == null) {
            System.out.println("Pedido nao existe");
        }
        return z;

    }
}
