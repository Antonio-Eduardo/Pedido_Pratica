package services.implement;

import Excecoes.CaixaLimiteExcedido;
import services.Caixa;
import entities.Pedido;

public class ServicoCaixaRapido implements Caixa {
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
