import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Scanner;

public class Search {
    public static void searchingClients() {
        try (Scanner sc = new Scanner(System.in)) {
            Connection connection = Connecting.getConnection();

            System.out.println("Qual é o seu nome?");
            String name = sc.nextLine();

            String sql = "SELECT * FROM clientes where nome = ?";
            PreparedStatement searchingClients = connection.prepareStatement(sql);
            searchingClients.setString(1, name);
            ResultSet resultado = searchingClients.executeQuery();

            if (resultado.next()) {
                System.out.println("ID do cliente: " + resultado.getInt(1));
                System.out.println("Nome do cliente: " + resultado.getString(2));
                System.out.println("Sexo do cliente: " + resultado.getString(3));
                System.out.println("Endereço do cliente: " + resultado.getString(4));
                System.out.println("Data do cadastro: " + resultado.getString(5));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
