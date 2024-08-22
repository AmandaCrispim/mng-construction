package dao;

import entities.Operario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OperarioDAO {
    public void inserir(Operario operario) throws SQLException {
        String sql = "INSERT INTO Operario (Nome_Operario, Funcao) VALUES (?, ?)";
        try (Connection con = ConexaoBD.getInstancia().getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, operario.getNomeOperario());
            stmt.setString(2, operario.getFuncao());
            stmt.executeUpdate();
        }
    }

    public void atualizar(Operario operario) throws SQLException {
        String sql = "UPDATE Operario SET Nome_Operario = ?, Funcao = ? WHERE ID_Operario = ?";
        try (Connection con = ConexaoBD.getInstancia().getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, operario.getNomeOperario());
            stmt.setString(2, operario.getFuncao());
            stmt.setInt(3, operario.getIdOperario());
            stmt.executeUpdate();
        }
    }

    public void excluir(int idOperario) throws SQLException {
        String sql = "DELETE FROM Operario WHERE ID_Operario = ?";
        try (Connection con = ConexaoBD.getInstancia().getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idOperario);
            stmt.executeUpdate();
        }
    }

    public Operario buscarPorId(int idOperario) throws SQLException {
        String sql = "SELECT * FROM Operario WHERE ID_Operario = ?";
        try (Connection con = ConexaoBD.getInstancia().getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idOperario);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Operario operario = new Operario();
                operario.setIdOperario(rs.getInt("ID_Operario"));
                operario.setNomeOperario(rs.getString("Nome_Operario"));
                operario.setFuncao(rs.getString("Funcao"));
                return operario;
            }
            return null;
        }
    }

    public List<Operario> listarTodos() throws SQLException {
        String sql = "SELECT * FROM Operario";
        List<Operario> operarios = new ArrayList<>();
        try (Connection con = ConexaoBD.getInstancia().getConexao();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Operario operario = new Operario();
                operario.setIdOperario(rs.getInt("ID_Operario"));
                operario.setNomeOperario(rs.getString("Nome_Operario"));
                operario.setFuncao(rs.getString("Funcao"));
                operarios.add(operario);
            }
        }
        return operarios;
    }
}

