import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Fornecedor {

    String descricao = "";
    String contato = "";
    String endereco = "";
    Scanner sc = new Scanner(System.in);

    public Fornecedor() {
        System.out.println("Digite o nome do seu fornecedor: ");
        this.descricao = sc.nextLine();
        System.out.println("Digite o contato do seu fornecedor: ");
        this.contato = sc.nextLine();
        System.out.println("Digite o endereço do seu fornecedor: ");
        this.endereco = sc.nextLine();
    }

    public static void criar_fornecedor(String descricao, String contato, String endereco) {
        try (Connection connection = Conexao.getConnection()) {

            String sql = "INSERT INTO fornecedor (descricao,contato,endereco) VALUES (?,?,?)";
            try (PreparedStatement inserindo_cliente = connection.prepareStatement(sql)) {
                inserindo_cliente.setString(1, descricao);
                inserindo_cliente.setString(2, contato);
                inserindo_cliente.setString(3, endereco);
                inserindo_cliente.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }
}
