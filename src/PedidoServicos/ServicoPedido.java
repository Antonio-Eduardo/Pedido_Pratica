package PedidoServicos;

import entities.Cliente;
import entities.ItensPedido;
import entities.Pedido;
import ENUM.StatusPedido;

import java.time.LocalDate;

public class ServicoPedido {

        public Pedido criarPedido() {
            return new Pedido();
        }

        public void adicionarItem(Pedido pedido, ItensPedido item) {
            pedido.addItem(item);
        }

        public void fecharPedido(Pedido pedido) {
            ServicoCalculo calculo = new ServicoCalculo();
            calculo.calculoPedido(pedido);

            pedido.setMomentoDaCompra(LocalDate.now());
            pedido.setStatus(StatusPedido.PAGAMENTO_PENDENTE);
        }
    public Pedido getPedidos(Cliente cliente){
        Pedido c = cliente.getPedidos().stream().filter(x -> x.getStatus() == StatusPedido.PAGAMENTO_PENDENTE).findFirst().orElse(null);
        if (c == null){
            System.out.println("vazio");
            return null;
        }
        return c;
    }
}
