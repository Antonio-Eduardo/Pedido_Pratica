package dao.impl;

import dao.ItensPedidoDAO;
import db.DB;
import entities.ItensPedido;
import entities.Pedido;
import entities.Produto;
import exceptions.DbException;
import factory.DaoFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItensPedidoDaoImpl implements ItensPedidoDAO {
    @Override
    public List<ItensPedido> buscarItensDoPedido(Long id) {
        String sql = "SELECT * FROM itens_pedidos WHERE idPedido = ?";
        List<ItensPedido> lista = new ArrayList<>();
        try (Connection conn = DB.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setLong(1, id);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Long idItem = rs.getLong("idItens");
                    int quantidade = rs.getInt("quantidade");
                    double preco = rs.getDouble("preco_unitario");
                    Long idProduto = rs.getLong("idProduto");
                    Long idPedido = rs.getLong("idPedido");

                    ItensPedido item = new ItensPedido(idItem, quantidade, preco, idProduto, idPedido);
                    lista.add(item);
                }
            }
        } catch (SQLException e) {
            throw new DbException();
        }
        return lista;
    }
    @Override
    public void salvar(Produto prod, Pedido ped, ItensPedido itensPedido) {
        String sql = "INSERT INTO itens_pedidos (quantidade,preco_unitario,idProduto,idPedido) VALUES (?,?,?,?)";
        try (Connection conn = DB.getConnection();
             PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            st.setInt(1, itensPedido.getQuantidade());
            st.setDouble(2, itensPedido.getPreco());
            st.setLong(3, prod.getiD());
            st.setLong(4, ped.getIdPedido());

            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet rs = st.getGeneratedKeys()) {
                    if (rs.next()) {
                        itensPedido.setiD(rs.getLong(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new DbException();
        }
    }
    @Override
    public void deleteItem(Long id) {
        String sql = "DELETE FROM itens_pedidos WHERE idItens = ?";
        try(Connection conn = DB.getConnection();
        PreparedStatement st = conn.prepareStatement(sql)){
            st.setLong(1,id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException();
        }
    }
}


