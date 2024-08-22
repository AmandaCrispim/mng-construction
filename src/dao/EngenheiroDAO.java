package dao;

import entities.Engenheiro;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EngenheiroDAO {
    public void inserir(Engenheiro engenheiro) throws SQLException {
        String sql = "INSERT INTO Engenheiro (Nome_Engenheiro, Especialidade) VALUES (?, ?)";
        try (Connection con = ConexaoBD.getInstancia().getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, engenheiro.getNomeEngenheiro());
            stmt.setString(2, engenheiro.getEspecialidade());
            stmt.executeUpdate();
        }
    }

    public void atualizar(Engenheiro engenheiro) throws SQLException {
        String sql = "UPDATE Engenheiro SET Nome_Engenheiro = ?, Especialidade = ? WHERE ID_Engenheiro = ?";
        try (Connection con = ConexaoBD.getInstancia().getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, engenheiro.getNomeEngenheiro());
            stmt.setString(2, engenheiro.getEspecialidade());
            stmt.setInt(3, engenheiro.getIdEngenheiro());
            stmt.executeUpdate();
        }
    }

    public void excluir(int idEngenheiro) throws SQLException {
        String sql = "DELETE FROM Engenheiro WHERE ID_Engenheiro = ?";
        try (Connection con = ConexaoBD.getInstancia().getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idEngenheiro);
            stmt.executeUpdate();
        }
    }

    public Engenheiro buscarPorId(int idEngenheiro) throws SQLException {
        String sql = "SELECT * FROM Engenheiro WHERE ID_Engenheiro = ?";
        try (Connection con = ConexaoBD.getInstancia().getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idEngenheiro);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Engenheiro engenheiro = new Engenheiro();
                engenheiro.setIdEngenheiro(rs.getInt("ID_Engenheiro"));
                engenheiro.setNomeEngenheiro(rs.getString("Nome_Engenheiro"));
                engenheiro.setEspecialidade(rs.getString("Especialidade"));
                return engenheiro;
            }
            return null;
        }
    }

    public List<Engenheiro> listarTodos() throws SQLException {
        String sql = "SELECT * FROM Engenheiro";
        List<Engenheiro> engenheiros = new ArrayList<>();
        try (Connection con = ConexaoBD.getInstancia().getConexao();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Engenheiro engenheiro = new Engenheiro();
                engenheiro.setIdEngenheiro(rs.getInt("ID_Engenheiro"));
                engenheiro.setNomeEngenheiro(rs.getString("Nome_Engenheiro"));
                engenheiro.setEspecialidade(rs.getString("Especialidade"));
                engenheiros.add(engenheiro);
            }
        }
        return engenheiros;
    }
}

