import entities.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Programa {
    public static void main(String[] args) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Date dataDeNascimento = sdf.parse("18/06/2003");
        Cliente cliente1 = new Cliente("Antonio Eduardo Moreira Oliveira", "scratbis@hotmail.com",
                dataDeNascimento);

        Date horaDaCompra = sdf.parse("29/04/2026");
        Pedido pedido = new Pedido(horaDaCompra,StatusPedido.PROCESSANDO,
                cliente1);

        Produto x1 = new Produto("Coca-Cola",6.90);
        Produto x2 = new Produto("Fanta-Laranja",4.90);

        ItensPedido itensEscolhidos = new ItensPedido(4,6.90,x1);
        ItensPedido itensEscolhidos1 = new ItensPedido(3, 4.90,x2);

        pedido.addItem(itensEscolhidos);
        pedido.addItem(itensEscolhidos1);

        pedido.totaL();
        System.out.println(pedido);

    }
}
