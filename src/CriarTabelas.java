import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CriarTabelas {

    private static final String URL = "jdbc:sqlite:gerenciamento_obras.db";

    public static void main(String[] args) {
        try {
            // Conectar ao banco de dados
            Connection con = DriverManager.getConnection(URL);
            Statement stmt = con.createStatement();

            // Criação das tabelas
            String criarTabelaProjeto = "CREATE TABLE IF NOT EXISTS Projeto (" +
                    "ID_Projeto INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "Nome_Projeto TEXT NOT NULL," +
                    "Local TEXT NOT NULL," +
                    "Data_Inicio DATE NOT NULL," +
                    "Data_Termino DATE NOT NULL" +
                    ");";

            String criarTabelaEngenheiro = "CREATE TABLE IF NOT EXISTS Engenheiro (" +
                    "ID_Engenheiro INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "Nome_Engenheiro TEXT NOT NULL," +
                    "Especialidade TEXT NOT NULL" +
                    ");";

            String criarTabelaOperario = "CREATE TABLE IF NOT EXISTS Operario (" +
                    "ID_Operario INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "Nome_Operario TEXT NOT NULL," +
                    "Funcao TEXT NOT NULL" +
                    ");";

            String criarTabelaEquipamento = "CREATE TABLE IF NOT EXISTS Equipamento (" +
                    "ID_Equipamento INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "Nome_Equipamento TEXT NOT NULL," +
                    "Tipo TEXT NOT NULL" +
                    ");";

            String criarTabelaMaterial = "CREATE TABLE IF NOT EXISTS Material (" +
                    "ID_Material INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "Nome_Material TEXT NOT NULL," +
                    "Quantidade INTEGER NOT NULL" +
                    ");";

            String criarTabelaAlocacaoEngenheiro = "CREATE TABLE IF NOT EXISTS Alocacao_Engenheiro (" +
                    "ID_Projeto INTEGER NOT NULL," +
                    "ID_Engenheiro INTEGER NOT NULL," +
                    "PRIMARY KEY (ID_Projeto, ID_Engenheiro)," +
                    "FOREIGN KEY (ID_Projeto) REFERENCES Projeto (ID_Projeto) ON DELETE CASCADE," +
                    "FOREIGN KEY (ID_Engenheiro) REFERENCES Engenheiro (ID_Engenheiro) ON DELETE CASCADE" +
                    ");";

            String criarTabelaAlocacaoOperario = "CREATE TABLE IF NOT EXISTS Alocacao_Operario (" +
                    "ID_Projeto INTEGER NOT NULL," +
                    "ID_Operario INTEGER NOT NULL," +
                    "PRIMARY KEY (ID_Projeto, ID_Operario)," +
                    "FOREIGN KEY (ID_Projeto) REFERENCES Projeto (ID_Projeto) ON DELETE CASCADE," +
                    "FOREIGN KEY (ID_Operario) REFERENCES Operario (ID_Operario) ON DELETE CASCADE" +
                    ");";

            String criarTabelaUsoEquipamento = "CREATE TABLE IF NOT EXISTS Uso_Equipamento (" +
                    "ID_Projeto INTEGER NOT NULL," +
                    "ID_Equipamento INTEGER NOT NULL," +
                    "PRIMARY KEY (ID_Projeto, ID_Equipamento)," +
                    "FOREIGN KEY (ID_Projeto) REFERENCES Projeto (ID_Projeto) ON DELETE CASCADE," +
                    "FOREIGN KEY (ID_Equipamento) REFERENCES Equipamento (ID_Equipamento) ON DELETE CASCADE" +
                    ");";

            String criarTabelaConsumoMaterial = "CREATE TABLE IF NOT EXISTS Consumo_Material (" +
                    "ID_Projeto INTEGER NOT NULL," +
                    "ID_Material INTEGER NOT NULL," +
                    "PRIMARY KEY (ID_Projeto, ID_Material)," +
                    "FOREIGN KEY (ID_Projeto) REFERENCES Projeto (ID_Projeto) ON DELETE CASCADE," +
                    "FOREIGN KEY (ID_Material) REFERENCES Material (ID_Material) ON DELETE CASCADE" +
                    ");";

            // Executar as instruções para criar as tabelas
            stmt.execute(criarTabelaProjeto);
            stmt.execute(criarTabelaEngenheiro);
            stmt.execute(criarTabelaOperario);
            stmt.execute(criarTabelaEquipamento);
            stmt.execute(criarTabelaMaterial);
            stmt.execute(criarTabelaAlocacaoEngenheiro);
            stmt.execute(criarTabelaAlocacaoOperario);
            stmt.execute(criarTabelaUsoEquipamento);
            stmt.execute(criarTabelaConsumoMaterial);

            System.out.println("Tabelas criadas com sucesso!");

            // Fechar a conexão
            stmt.close();
            con.close();

        } catch (SQLException e) {
            System.err.println("Erro ao criar tabelas: " + e.getMessage());
        }
    }
}

