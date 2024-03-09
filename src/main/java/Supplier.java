import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Supplier {

    String description;
    String contact;
    String adress;
    Scanner sc = new Scanner(System.in);

    public Supplier() {
        System.out.println("Digite o nome do seu fornecedor: ");
        this.description = sc.nextLine();
        System.out.println("Digite o contato do seu fornecedor: ");
        this.contact = sc.nextLine();
        System.out.println("Digite o endereço do seu fornecedor: ");
        this.adress = sc.nextLine();

        createSupplier(description,contact,adress);
    }

    public static void createSupplier(String description, String contact, String adress) {
        try (Connection connection = Connecting.getConnection()) {

            String sql = "INSERT INTO fornecedor (descricao,contato,endereco) VALUES (?,?,?)";
            try (PreparedStatement enteringSupplier = connection.prepareStatement(sql)) {
                enteringSupplier.setString(1, description);
                enteringSupplier.setString(2, contact);
                enteringSupplier.setString(3, adress);
                enteringSupplier.executeUpdate();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        } catch (SQLException exception) {
            throw new RuntimeException();
        }
    }
}
