import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Clientes {

    String nome;
    String sexo;
    String endereco;
    Scanner sc = new Scanner(System.in);

    public Clientes() {
        System.out.println("Digite o nome do cliente ");
        this.nome = sc.nextLine();
        System.out.println("Digite o sexo do cliente \n M para Masculino \n F para Feminino");
        this.sexo = sc.nextLine();
        System.out.println("Digite o endereço do cliente: ");
        this.endereco = sc.nextLine();
    }
    public static void criar_cliente(String nome, String sexo, String endereco) {
        try {
            Connection connection = Conexao.getConnection();

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
