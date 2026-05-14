package dao.impl;

import dao.PedidoDAO;
import db.DB;
import entities.Cliente;
import entities.Pedido;
import enums.StatusPedido;

import java.sql.*;

public class PedidoDaoImpl implements PedidoDAO {
    @Override
    public Pedido buscarPedidoPorId(Long idPedido, Cliente cliente) {
        String sql = "SELECT * FROM pedido WHERE idPedido = ? AND idCliente = ?";
        try(Connection conn = DB.getConnection();
        PreparedStatement st = conn.prepareStatement(sql)){
            st.setLong(1,idPedido);
            st.setLong(2,cliente.getiD());
            try(ResultSet rs = st.executeQuery()){
                if (rs.next()){
                    Long idPedidoDB = rs.getLong("idPedido");
                    Timestamp dataHora = rs.getTimestamp("dataHora");
                    double valor = rs.getDouble("valor");
                    Long idClienteDB = rs.getLong("idCliente");
                    StatusPedido status = StatusPedido.valueOf(rs.getString("status"));

                    return new Pedido(idPedido, idClienteDB,valor,dataHora,status);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    @Override
    public void salvar(Cliente c, Pedido p) {
        String sql = "INSERT INTO pedido (idCliente,status) VALUES (?,?)";
        try(Connection conn = DB.getConnection();
            PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            st.setLong(1,c.getiD());
            st.setString(2,p.getStatus().name());
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

    @Override
    public void updatePedido(Pedido pedido) {
        String sql = "UPDATE pedido SET valor = ?,status = ? WHERE idPedido = ?";
        try(Connection conn = DB.getConnection();
        PreparedStatement st = conn.prepareStatement(sql)){
            st.setDouble(1,pedido.getPrecoPedido());
            st.setString(2,pedido.getStatus().name());
            st.setLong(3,pedido.getIdPedido());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
