import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connecting {
    private static Connection connection;

    // Método para obter a conexão
    public static Connection getConnection() throws SQLException {
        // Verifica se a conexão já foi criada e está aberta
        if (connection == null || connection.isClosed()) {
            // Se não, cria uma nova conexão
            connection = DriverManager.getConnection("endereco_do_seu_banco", "seu_usuario", "sua_senha");
        }
        return connection;
    }

    // Método para fechar a conexão
    public static void closeConnection() throws SQLException {
        // Verifica se a conexão não é nula e está aberta
        if (connection != null && !connection.isClosed()) {
            // Se sim, fecha a conexão
            connection.close();
        }
    }
}
