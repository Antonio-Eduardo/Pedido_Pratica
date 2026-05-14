package application;

import dao.ItensPedidoDAO;
import entities.ItensPedido;
import entities.Pedido;
import entities.Produto;
import factory.DaoFactory;

public class ServiceItemPedido {

    ItensPedidoDAO itemPedidoDAO = DaoFactory.criarItensPedidoDao();

    public ItensPedido criarItem(Pedido pedido, int quantidade, double preco, Produto prod){
       ItensPedido ip = new ItensPedido(quantidade,preco,prod.getiD(), pedido.getIdPedido());
        itemPedidoDAO.salvar(prod,pedido,ip);
        return ip;
    }
}
