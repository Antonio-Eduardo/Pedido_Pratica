package CaixaServico;

import Excecoes.CaixaLimiteExcedido;
import entities.Pedido;

public class CaixaRapido implements Caixa{
    @Override
    public void processarPedido(Pedido pedido) {
        if (pedido.getItensPedidos().size() > 15){
            throw new CaixaLimiteExcedido();
        }
        double total = pedido.getPrecoPedido();

        System.out.println("Processando pedido..");
        System.out.println("Total: " + total);
        pedido.finalizarPedido();
    }
}
