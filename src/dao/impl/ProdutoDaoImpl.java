package dao.impl;

import dao.ProdutoDAO;
import db.DB;
import entities.Produto;

import java.sql.*;

public class ProdutoDaoImpl implements ProdutoDAO {
    @Override
    public Produto buscarProdutoPorId(Long id) {
        String sql = "SELECT * FROM produto WHERE idProduto =  ?";
        try(Connection conn = DB.getConnection();
        PreparedStatement st = conn.prepareStatement(sql)){
            st.setLong(1,id);
            try(ResultSet rs = st.executeQuery()){
                if(rs.next()) {
                    Long idDB = rs.getLong("idProduto");
                    String nome = rs.getString("nome");
                    double preco = rs.getDouble("preco");

                    return new Produto(idDB, nome, preco);

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void salvar(Produto p) {
        String sql = "INSERT INTO produto (nome,preco) VALUES (?,?)";
        try (Connection conn = DB.getConnection();
             PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            st.setString(1, p.getNome());
            st.setDouble(2, p.getPreco());

            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet rsGenerated = st.getGeneratedKeys()) {
                    if (rsGenerated.next()) {
                        p.setiD(rsGenerated.getLong(1));

                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deletarProdutoPorId(Long id) {
        String sql = "DELETE FROM produto WHERE idProduto = ?";
        try(Connection conn = DB.getConnection();
        PreparedStatement st = conn.prepareStatement(sql)){
            st.setLong(1,id);

            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
