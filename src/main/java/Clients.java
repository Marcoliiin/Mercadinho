import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Clients {

    String name;
    String sex;
    String adress;
    Scanner sc = new Scanner(System.in);

    public Clients() {
        System.out.println("Digite o nome do cliente: ");
        this.name = sc.nextLine();
        System.out.println("Digite o sexo do cliente: \nM para Masculino. \nF para Feminino.");
        this.sex = sc.nextLine();
        System.out.println("Digite o endereço do cliente: ");
        this.adress = sc.nextLine();
    }
    public static void createClient(String name, String sex, String adress) {
        try {
            Connection connection = Connecting.getConnection();

            String sql = "INSERT INTO clientes (name,sex,adress) VALUES (?,?,?)";
            try (PreparedStatement enteringClient = connection.prepareStatement(sql)) {
                enteringClient.setString(1, name);
                enteringClient.setString(2, sex);
                enteringClient.setString(3, adress);
                enteringClient.executeUpdate();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        } catch (SQLException exception) {
            throw new RuntimeException();
        }
    }
}
