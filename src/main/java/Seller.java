import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Seller {
    private final String name;
    private final Scanner sc = new Scanner(System.in);

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
            } catch (SQLException exception) {
                System.err.println("Erro ao cadastrar o venddor: " + exception.getMessage());
                exception.printStackTrace();
            }
        } catch (SQLException exception) {
            System.err.println("Erro ao se conectar ao banco: " + exception.getMessage());
            exception.printStackTrace();
        }
    }
}
