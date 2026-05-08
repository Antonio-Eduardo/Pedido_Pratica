package CaixaServico;

import entities.Cliente;
import entities.Pedido;

public interface Caixa {
    public void processarPedido( Pedido pedido);
}
