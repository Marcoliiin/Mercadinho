import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class _item {
    public static void main(String[] args) {
    }

    public static int consultar_id_produto() {
        int retorno_consulta_produto = 0;

        try (Scanner sc = new Scanner(System.in);
             Connection connection = Conexao.getConnection()) {

            System.out.println("Qual o ID do item que foi vendido?");
            int id_item = sc.nextInt();

            String sql = "SELECT ID from produto where id = ?";
            try (PreparedStatement consultando_id = connection.prepareStatement(sql)) {
                consultando_id.setInt(1, id_item);

                try (ResultSet query = consultando_id.executeQuery()) {
                    if (query.next()) {
                        retorno_consulta_produto = query.getInt(1);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
        return retorno_consulta_produto;
    }

    public static int consultar_vendedor_id_fornecedor(){

      return 0;
    }
}
