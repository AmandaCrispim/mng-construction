package dao;

import entities.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjetoDAO {
    public void inserir(Projeto projeto) throws SQLException {
        String sql = "INSERT INTO Projeto (Nome_Projeto, Local, Data_Inicio, Data_Termino) VALUES (?, ?, ?, ?)";
        try (Connection con = ConexaoBD.getInstancia().getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, projeto.getNomeProjeto());
            stmt.setString(2, projeto.getLocal());
            stmt.setDate(3, new java.sql.Date(projeto.getDataInicio().getTime()));
            stmt.setDate(4, new java.sql.Date(projeto.getDataTermino().getTime()));
            stmt.executeUpdate();
        }
    }

    public void atualizar(Projeto projeto) throws SQLException {
        String sql = "UPDATE Projeto SET Nome_Projeto = ?, Local = ?, Data_Inicio = ?, Data_Termino = ? WHERE ID_Projeto = ?";
        try (Connection con = ConexaoBD.getInstancia().getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, projeto.getNomeProjeto());
            stmt.setString(2, projeto.getLocal());
            stmt.setDate(3, new java.sql.Date(projeto.getDataInicio().getTime()));
            stmt.setDate(4, new java.sql.Date(projeto.getDataTermino().getTime()));
            stmt.setInt(5, projeto.getIdProjeto());
            stmt.executeUpdate();
        }
    }

    public void excluir(int idProjeto) throws SQLException {
        String sql = "DELETE FROM Projeto WHERE ID_Projeto = ?";
        try (Connection con = ConexaoBD.getInstancia().getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idProjeto);
            stmt.executeUpdate();
        }
    }

    public Projeto buscarPorId(int idProjeto) throws SQLException {
        String sql = "SELECT * FROM Projeto WHERE ID_Projeto = ?";
        try (Connection con = ConexaoBD.getInstancia().getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idProjeto);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Projeto projeto = new Projeto();
                projeto.setIdProjeto(rs.getInt("ID_Projeto"));
                projeto.setNomeProjeto(rs.getString("Nome_Projeto"));
                projeto.setLocal(rs.getString("Local"));
                projeto.setDataInicio(rs.getDate("Data_Inicio"));
                projeto.setDataTermino(rs.getDate("Data_Termino"));
                return projeto;
            }
            return null;
        }
    }

    public List<Projeto> listarTodos() throws SQLException {
        String sql = "SELECT * FROM Projeto";
        List<Projeto> projetos = new ArrayList<>();
        try (Connection con = ConexaoBD.getInstancia().getConexao();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Projeto projeto = new Projeto();
                projeto.setIdProjeto(rs.getInt("ID_Projeto"));
                projeto.setNomeProjeto(rs.getString("Nome_Projeto"));
                projeto.setLocal(rs.getString("Local"));
                projeto.setDataInicio(rs.getDate("Data_Inicio"));
                projeto.setDataTermino(rs.getDate("Data_Termino"));
                projetos.add(projeto);
            }
        }
        return projetos;
    }

    // Listar engenheiros e operários alocados em um projeto
    public List<Engenheiro> listarEngenheirosPorProjeto(int idProjeto) throws SQLException {
        String sql = "SELECT e.* FROM Engenheiro e JOIN Alocacao_Engenheiro a ON e.ID_Engenheiro = a.ID_Engenheiro WHERE a.ID_Projeto = ?";
        List<Engenheiro> engenheiros = new ArrayList<>();
        try (Connection con = ConexaoBD.getInstancia().getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idProjeto);
            ResultSet rs = stmt.executeQuery();
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

    public List<Operario> listarOperariosPorProjeto(int idProjeto) throws SQLException {
        String sql = "SELECT o.* FROM Operario o JOIN Alocacao_Operario a ON o.ID_Operario = a.ID_Operario WHERE a.ID_Projeto = ?";
        List<Operario> operarios = new ArrayList<>();
        try (Connection con = ConexaoBD.getInstancia().getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idProjeto);
            ResultSet rs = stmt.executeQuery();
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

    // Listar equipamentos e materiais utilizados em um projeto
    public List<Equipamento> listarEquipamentosPorProjeto(int idProjeto) throws SQLException {
        String sql = "SELECT e.* FROM Equipamento e JOIN Uso_Equipamento u ON e.ID_Equipamento = u.ID_Equipamento WHERE u.ID_Projeto = ?";
        List<Equipamento> equipamentos = new ArrayList<>();
        try (Connection con = ConexaoBD.getInstancia().getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idProjeto);
            ResultSet rs = stmt.executeQuery();
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

    public List<Material> listarMateriaisPorProjeto(int idProjeto) throws SQLException {
        String sql = "SELECT m.* FROM Material m JOIN Consumo_Material c ON m.ID_Material = c.ID_Material WHERE c.ID_Projeto = ?";
        List<Material> materiais = new ArrayList<>();
        try (Connection con = ConexaoBD.getInstancia().getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idProjeto);
            ResultSet rs = stmt.executeQuery();
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

