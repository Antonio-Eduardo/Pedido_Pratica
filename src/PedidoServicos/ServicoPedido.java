package PedidoServicos;

import entities.ItensPedido;
import entities.Pedido;
import entities.StatusPedido;

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
            pedido.setStatus(StatusPedido.PROCESSANDO);
        }
}
