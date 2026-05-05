package PedidoServicos;

import entities.ItensPedido;
import entities.Pedido;

public class ServicoCalculo {
    public void calculoPedido(Pedido pedido){
        double soma = 0.0;
        for (ItensPedido x : pedido.getItensPedidos()){
            soma += (x.getPreco() * x.getQuantidade());
        } pedido.setPrecoPedido(soma);
    }
}
