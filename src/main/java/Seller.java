import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Seller {
    String name;
    Scanner sc = new Scanner(System.in);

    public Seller() {
        System.out.println("Digite o nome do vendedor:");
        this.name = sc.nextLine();

        createSeller(name);
    }

    public static void createSeller(String name) {
        try (Connection connection = Connecting.getConnection()) {

            String sql = "INSERT INTO vendedor (nome) VALUES (?)";
            try (PreparedStatement enteringSeller = connection.prepareStatement(sql)) {
                enteringSeller.setString(1, name);

                enteringSeller.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }
}
