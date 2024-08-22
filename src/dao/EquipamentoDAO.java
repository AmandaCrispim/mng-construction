package dao;

import entities.Equipamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipamentoDAO {
    public void inserir(Equipamento equipamento) throws SQLException {
        String sql = "INSERT INTO Equipamento (Nome_Equipamento, Tipo) VALUES (?, ?)";
        try (Connection con = ConexaoBD.getInstancia().getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, equipamento.getNomeEquipamento());
            stmt.setString(2, equipamento.getTipo());
            stmt.executeUpdate();
        }
    }

    public void atualizar(Equipamento equipamento) throws SQLException {
        String sql = "UPDATE Equipamento SET Nome_Equipamento = ?, Tipo = ? WHERE ID_Equipamento = ?";
        try (Connection con = ConexaoBD.getInstancia().getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, equipamento.getNomeEquipamento());
            stmt.setString(2, equipamento.getTipo());
            stmt.setInt(3, equipamento.getIdEquipamento());
            stmt.executeUpdate();
        }
    }

    public void excluir(int idEquipamento) throws SQLException {
        String sql = "DELETE FROM Equipamento WHERE ID_Equipamento = ?";
        try (Connection con = ConexaoBD.getInstancia().getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idEquipamento);
            stmt.executeUpdate();
        }
    }

    public Equipamento buscarPorId(int idEquipamento) throws SQLException {
        String sql = "SELECT * FROM Equipamento WHERE ID_Equipamento = ?";
        try (Connection con = ConexaoBD.getInstancia().getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idEquipamento);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Equipamento equipamento = new Equipamento();
                equipamento.setIdEquipamento(rs.getInt("ID_Equipamento"));
                equipamento.setNomeEquipamento(rs.getString("Nome_Equipamento"));
                equipamento.setTipo(rs.getString("Tipo"));
                return equipamento;
            }
            return null;
        }
    }

    public List<Equipamento> listarTodos() throws SQLException {
        String sql = "SELECT * FROM Equipamento";
        List<Equipamento> equipamentos = new ArrayList<>();
        try (Connection con = ConexaoBD.getInstancia().getConexao();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Equipamento equipamento = new Equipamento();
                equipamento.setIdEquipamento(rs.getInt("ID_Equipamento"));
                equipamento.setNomeEquipamento(rs.getString("Nome_Equipamento"));
                equipamento.setTipo(rs.getString("Tipo"));
                equipamentos.add(equipamento);
            }
        }
        return equipamentos;
    }
}

