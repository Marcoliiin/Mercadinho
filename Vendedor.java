import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Vendedor {
    String nome = "";
    Scanner sc = new Scanner(System.in);

    public Vendedor() {
        System.out.println("Digite o nome do vendedor:");
        this.nome = sc.nextLine();

        criar_vendedor(nome);
    }

    public static void criar_vendedor(String nome) {
        try (Connection connection = Conexao.getConnection()) {

            String sql = "INSERT INTO vendedor (nome) VALUES (?)";
            try (PreparedStatement inserindo_cliente = connection.prepareStatement(sql)) {
                inserindo_cliente.setString(1, nome);

                inserindo_cliente.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }
}
