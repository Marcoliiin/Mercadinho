import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class clientes {
    public static void main(String[] args) {
    }

    public static void criar_cliente() {
        try {
            Connection connection = conexao.getConnection();

            Scanner sc = new Scanner(System.in);
            System.out.println("Digite o nome do cliente ");
            String nome = sc.nextLine();
            System.out.println("Digite o sexo do cliente \n M para Masculino \n F para Feminino");
            String sexo = sc.nextLine();
            System.out.println("Digite o endereço do cliente: ");
            String endereco = sc.nextLine();

            String sql = "INSERT INTO clientes (nome,sexo,endereco) VALUES (?,?,?)";
            try (PreparedStatement inserindo_cliente = connection.prepareStatement(sql)) {
                inserindo_cliente.setString(1, nome);
                inserindo_cliente.setString(2, sexo);
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
