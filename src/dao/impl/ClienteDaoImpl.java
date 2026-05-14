package dao.impl;

import dao.ClienteDAO;
import db.DB;
import entities.Cliente;

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
            throw new RuntimeException(e);
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
            throw new RuntimeException(e);
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
            throw new RuntimeException(e);
        }
    }
}
