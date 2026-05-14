package application;

import dao.PedidoDAO;
import entities.Cliente;
import entities.ItensPedido;
import entities.Pedido;
import enums.StatusPedido;
import factory.DaoFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ServicoPedido {
    PedidoDAO pedidoDAO = DaoFactory.criarPedidoDao();

    public void adicionarItem(Pedido pedido, ItensPedido item) {
        pedido.addItem(item);
    }
    private final ServicoCalculo calculo = new ServicoCalculo();

    public void fecharPedido(Long id,Cliente cliente) {
        Pedido p = pedidoDAO.buscarPedidoPorId(id,cliente);
        double total =calculo.calcularPedido(p);
        p.setStatus(StatusPedido.PAGAMENTO_PENDENTE);
        p.setPrecoPedido(total);
        pedidoDAO.updatePedido(p);
    }
    public Pedido criarPedido(Cliente cliente){
        Pedido pedido = new Pedido(cliente.getiD(),StatusPedido.PROCESSANDO);
        cliente.getPedidos().add(pedido);
        pedidoDAO.salvar(cliente,pedido);
        System.out.println("iD do pedido: " + pedido.getIdPedido());
        return pedido;
    }
}
