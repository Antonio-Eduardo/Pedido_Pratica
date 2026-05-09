package Application;

import ENUM.StatusPedido;
import entities.Cliente;
import entities.ItensPedido;
import entities.Pedido;

import java.time.LocalDateTime;
import java.util.List;

public class ServicoCalculo {
    public void calculoPedido(Cliente cliente){
        double somaI = 0.0;
        for (Pedido pedido : cliente.getPedidos()){
             for (ItensPedido p : pedido.getItensPedidos()){
                 somaI+= (p.getPreco() * p.getQuantidade());
             }
             pedido.setPrecoPedido(somaI);
            pedido.setMomentoDaCompra(LocalDateTime.now());
            pedido.setStatus(StatusPedido.PAGAMENTO_PENDENTE);
        }
    }
}
