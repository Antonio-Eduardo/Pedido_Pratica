package services.implement;

import ENUM.StatusPedido;
import services.Caixa;
import entities.Pedido;

public class ServicoCaixaDefault implements Caixa {
    @Override
    public void processarPedido(Pedido pedido) {
        double total = pedido.getPrecoPedido();
        System.out.println("Status: " + StatusPedido.PROCESSANDO);
        System.out.println("========================");
        System.out.println("Total: " + total);
        pedido.finalizarPedido();
    }
}
