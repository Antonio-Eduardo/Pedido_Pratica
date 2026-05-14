package dao.impl;

import dao.PedidoDAO;
import db.DB;
import entities.Cliente;
import entities.Pedido;
import enums.StatusPedido;

import java.sql.*;

public class PedidoDaoImpl implements PedidoDAO {
    @Override
    public Pedido buscarPedidoPorId(Long id) {
        String sql = "SELECT * FROM pedido WHERE idPedido = ?";
        try(Connection conn = DB.getConnection();
        PreparedStatement st = conn.prepareStatement(sql)){
            st.setLong(1,id);
            try(ResultSet rs = st.executeQuery()){
                if (rs.next()){
                    Long idPedido = rs.getLong("idPedido");
                    Timestamp dataHora = rs.getTimestamp("dataHora");
                    double valor = rs.getDouble("valor");
                    Long idCliente = rs.getLong("idCliente");
                    StatusPedido status = StatusPedido.valueOf(rs.getString("status"));

                    return new Pedido(idPedido, idCliente,valor,dataHora,status);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    @Override
    public void salvar(Cliente c, Pedido p) {
        String sql = "INSERT INTO pedido (valor,idCliente,status) VALUES (?,?,?)";
        try(Connection conn = DB.getConnection();
            PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            st.setDouble(1,p.getPrecoPedido());
            st.setLong(2,c.getiD());
            st.setString(3,p.getStatus().name());
           int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0){
                try(ResultSet rsGenerated = st.getGeneratedKeys()){
                    p.setIdPedido(rsGenerated.getLong(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deletarPedidoPorId(Long id) {
        String sql = "DELETE FROM pedido WHERE idPedido = ?";
        try(Connection conn = DB.getConnection();
        PreparedStatement st = conn.prepareStatement(sql)){
            st.setLong(1,id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
