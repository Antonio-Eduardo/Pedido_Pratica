package Application;

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

    public void fecharPedido(Cliente cliente) {
        ServicoCalculo calculo = new ServicoCalculo();
        calculo.calculoPedido(cliente);
    }

    public Pedido getPedidos(Cliente cliente) {
        Pedido c = cliente.getPedidos().stream().filter(x -> x.getStatus() == StatusPedido.PAGAMENTO_PENDENTE).findFirst().orElse(null);
        if (c == null) {
            System.out.println("vazio");
            return null;
        }
        return c;
    }

    public Pedido buscarPedido(List<Pedido> pedidos, UUID codigo) {
        Pedido z = pedidos.stream().filter(c -> c.getIdPedido().equals(codigo)).findFirst().orElse(null);
        if (z == null) {
            System.out.println("Pedido nao existe");
        }
        return z;

    }
}
