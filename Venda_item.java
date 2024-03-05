import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Venda_item {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in);
             Connection connection = Conexao.getConnection()) {


       int id_venda = Consultar_ids.consultar_id_venda();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
