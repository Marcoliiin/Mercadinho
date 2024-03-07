import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Consultar_caracteristicas {
    public static int consultar_preco_produto(long id_produto) {
        int preco_produto = 0;

        try (Connection connection = Conexao.getConnection()) {
            String sql = "SELECT preco FROM produto where id = ?";
            try (PreparedStatement consultando_preco = connection.prepareStatement(sql)) {
                consultando_preco.setLong(1, id_produto);

                ResultSet query = consultando_preco.executeQuery();
                if (query.next()) {
                    preco_produto = query.getInt(1);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return preco_produto;
    }
}
