package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    private static ConexaoBD instancia;
    private Connection conexao;

    private ConexaoBD() throws SQLException {
        try {
            conexao = DriverManager.getConnection("jdbc:sqlite:meu_banco.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized ConexaoBD getInstancia() throws SQLException {
        if (instancia == null) {
            instancia = new ConexaoBD();
        } else if (instancia.getConexao().isClosed()) {
            instancia = new ConexaoBD();
        }
        return instancia;
    }

    public Connection getConexao() {
        return conexao;
    }
}

