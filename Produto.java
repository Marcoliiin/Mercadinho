import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Produto {
    public static void cadastrar_produto() {

        int id_fornecedor = Vinculos.vinculo_produto_fornecedor();

        try (Scanner sc = new Scanner(System.in);
             Connection connection = Conexao.getConnection()) {

            System.out.println("Qual é a descrição do produto?");
            String descricao = sc.nextLine();

            System.out.println("Qual o preço deste produto?");
            double preco = sc.nextDouble();

            System.out.println("Qual é o estoque deste produto?");
            int estoque = sc.nextInt();


            String sintaxe = "INSERT INTO produto (descricao,preco,estoque,id_fornecedor) VALUES (?,?,?,?)";
            try (PreparedStatement inserindo = connection.prepareStatement(sintaxe)) {
                inserindo.setString(1, descricao);
                inserindo.setDouble(2, preco);
                inserindo.setInt(3, estoque);
                inserindo.setInt(4, id_fornecedor);

                inserindo.executeUpdate();

            } catch (SQLException ex) {
                throw new RuntimeException();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
