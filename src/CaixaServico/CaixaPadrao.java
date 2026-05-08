package CaixaServico;

import entities.Pedido;

public class CaixaPadrao implements Caixa{
    @Override
    public void processarPedido(Pedido pedido) {
        double total = pedido.getPrecoPedido();

        System.out.println("Processando pedido..");
        System.out.println("Total: " + total);
    }
}
