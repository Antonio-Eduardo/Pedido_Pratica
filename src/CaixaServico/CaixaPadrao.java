package CaixaServico;

import ENUM.StatusPedido;
import entities.Cliente;
import entities.Pedido;

public class CaixaPadrao implements Caixa{
    @Override
    public void processarPedido(Pedido pedido) {
        double total = pedido.getPrecoPedido();
        System.out.println("Status: " + StatusPedido.PROCESSANDO);
        System.out.println("========================");
        System.out.println("Total: " + total);
        pedido.finalizarPedido();
    }
}
