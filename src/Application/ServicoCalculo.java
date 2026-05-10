package Application;

import ENUM.StatusPedido;
import entities.Cliente;
import entities.ItensPedido;
import entities.Pedido;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ServicoCalculo {
    public double calcularPedido(Pedido pedido) {
        return pedido.getItensPedidos().stream().mapToDouble(p -> p.getPreco() * p.getQuantidade()).sum();
    }
}
