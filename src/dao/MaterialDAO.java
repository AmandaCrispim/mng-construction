package dao;

import entities.Material;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaterialDAO {
    public void inserir(Material material) throws SQLException {
        String sql = "INSERT INTO Material (Nome_Material, Quantidade) VALUES (?, ?)";
        try (Connection con = ConexaoBD.getInstancia().getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, material.getNomeMaterial());
            stmt.setInt(2, material.getQuantidade());
            stmt.executeUpdate();
        }
    }

    public void atualizar(Material material) throws SQLException {
        String sql = "UPDATE Material SET Nome_Material = ?, Quantidade = ? WHERE ID_Material = ?";
        try (Connection con = ConexaoBD.getInstancia().getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, material.getNomeMaterial());
            stmt.setInt(2, material.getQuantidade());
            stmt.setInt(3, material.getIdMaterial());
            stmt.executeUpdate();
        }
    }

    public void excluir(int idMaterial) throws SQLException {
        String sql = "DELETE FROM Material WHERE ID_Material = ?";
        try (Connection con = ConexaoBD.getInstancia().getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idMaterial);
            stmt.executeUpdate();
        }
    }

    public Material buscarPorId(int idMaterial) throws SQLException {
        String sql = "SELECT * FROM Material WHERE ID_Material = ?";
        try (Connection con = ConexaoBD.getInstancia().getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idMaterial);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Material material = new Material();
                material.setIdMaterial(rs.getInt("ID_Material"));
                material.setNomeMaterial(rs.getString("Nome_Material"));
                material.setQuantidade(rs.getInt("Quantidade"));
                return material;
            }
            return null;
        }
    }

    public List<Material> listarTodos() throws SQLException {
        String sql = "SELECT * FROM Material";
        List<Material> materiais = new ArrayList<>();
        try (Connection con = ConexaoBD.getInstancia().getConexao();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Material material = new Material();
                material.setIdMaterial(rs.getInt("ID_Material"));
                material.setNomeMaterial(rs.getString("Nome_Material"));
                material.setQuantidade(rs.getInt("Quantidade"));
                materiais.add(material);
            }
        }
        return materiais;
    }
}
