package dao.impl;

import dao.ClienteDAO;
import db.DB;
import entities.Cliente;
import entities.ItensPedido;
import entities.Pedido;
import enums.StatusPedido;
import exceptions.DbException;

import java.sql.*;
import java.time.LocalDate;

public class ClienteDaoImpl implements ClienteDAO {
    @Override
    public Cliente buscarContaPorId(Long id) {
        String sql = "SELECT * FROM cliente WHERE idCliente = ?";
        try(Connection conn = DB.getConnection();
        PreparedStatement st = conn.prepareStatement(sql)){
             st.setLong(1,id);
             try(ResultSet rs = st.executeQuery()){
                 if (rs.next()){
                     String nome = rs.getString("nome");
                     String email = rs.getString("email");
                     LocalDate dataDeNascimento = rs.getObject("dataDeNascimento", LocalDate.class);
                     String cpf = rs.getString("cpf");
                     Long idDb = rs.getLong("idCliente");

                     return new Cliente(idDb,nome,email,dataDeNascimento,cpf);
                 }
             }
        } catch (SQLException e) {
            throw new DbException();
        }
        return null;
    }
    @Override
    public void salvar(Cliente c) {
        String sql = "INSERT INTO cliente (nome,email,dataDeNascimento,cpf) VALUES (?,?,?,?)";
        try(Connection conn = DB.getConnection();
            PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1,c.getNome());
            st.setString(2,c.getEmail());
            st.setObject(3,c.getDataDeNascimento());
            st.setString(4, c.getCpf());

            st.executeUpdate();

            try(ResultSet rsGenerated = st.getGeneratedKeys()){
                if (rsGenerated.next()){
                    c.setiD(rsGenerated.getLong(1));
                }
            }
        } catch (SQLException e) {
            throw new DbException();
        }
    }
    @Override
    public void deletarCliente(Long id) {
        String sql = "DELETE FROM cliente WHERE idCliente = ?";
        try(Connection conn = DB.getConnection();
        PreparedStatement st = conn.prepareStatement(sql)){
            st.setLong(1,id);

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException();
        }
    }
    public Cliente ClientesPedidos(Long id) {
        String sql = "SELECT cli.idCliente, cli.nome AS cli_nome, cli.email, cli.dataDeNascimento, cli.cpf, " +
                "ped.idPedido, ped.dataHora, ped.valor, ped.status, " +
                "itp.idItens, itp.quantidade, itp.preco_unitario, itp.idProduto AS item_prod_id, " +
                "prd.nome AS prod_nome, prd.preco AS prod_preco " +
                "FROM cliente cli " +
                "JOIN pedido ped ON ped.idCliente = cli.idCliente " +
                "JOIN itens_pedidos itp ON itp.idPedido = ped.idPedido " +
                "JOIN produto prd ON prd.idProduto = itp.idProduto " +
                "WHERE cli.idCliente = ?";

        Cliente cliente = null;
        Pedido pedidoAtual = null;
        try (Connection conn = DB.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setLong(1, id);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    if (cliente == null) {
                        cliente = new Cliente(rs.getLong("idCliente"), rs.getString("cli_nome"),
                                rs.getString("email"), rs.getObject("dataDeNascimento", LocalDate.class),
                                rs.getString("cpf"));
                    }
                    long idPedidoRow = rs.getLong("idPedido");
                    if (pedidoAtual == null || pedidoAtual.getIdPedido() != idPedidoRow) {
                        pedidoAtual = new Pedido(idPedidoRow, cliente.getiD(), rs.getDouble("valor"),
                                rs.getTimestamp("dataHora"), StatusPedido.valueOf(rs.getString("status")));
                        cliente.addPedido(pedidoAtual);
                    }
                    long idItem = rs.getLong("idItens");
                    if (idItem != 0) {
                        ItensPedido item = new ItensPedido(idItem, rs.getInt("quantidade"),
                                rs.getDouble("preco_unitario"), rs.getLong("item_prod_id"),
                                idPedidoRow);
                        pedidoAtual.addItem(item);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DbException();
        }
        return cliente;
    }
}
